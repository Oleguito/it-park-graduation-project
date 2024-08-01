
'use client'
import axios from 'axios'
import forge from 'node-forge'
import queryString from 'query-string'
import {Settings} from '@/constants/settings'

const TOKEN_URL = Settings.keycloak.tokenUrl;
const KEYCLOAK_AUTH_URL = Settings.keycloak.authUrl;
const REDIRECT_URI = Settings.keycloak.redirectUrl


function generateRandomString(length) {
	let text = ''
	const possible =
		'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'

	for (let i = 0; i < length; i++) {
		text += possible.charAt(Math.floor(Math.random() * possible.length))
	}

	return text
}

function saveStateAndVerifier(state, codeVerifier) {
	if (window.location.search.includes('state')) return
	const storage = window.sessionStorage
	storage.clear()
	storage.setItem('state', state)
	storage.setItem('code_verifier', codeVerifier)
}

async function generateCodeChallenge(codeVerifier) {
	const sha256 = forge.md.sha256.create()
	sha256.update(codeVerifier)
	let digest = sha256.digest().getBytes()

	// Сначала мы переводим данные в Base64
	let base64 = forge.util.encode64(digest)

	// Затем мы заменяем некоторые символы согласно стандарту URL-safe base64
	return base64.replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '')
}

async function makeRedirectUrl(codeChallenge, state) {
	let queryParams = new URLSearchParams({
		client_id: Settings.keycloak.clientId,
		response_type: 'code',
		state: state,
		scope: 'openid profile email',
		code_challenge: codeChallenge,
		code_challenge_method: 'S256',
		redirect_uri: REDIRECT_URI,
	})

	let url = new URL(KEYCLOAK_AUTH_URL)
	url.search = queryParams

	return url.toString()
}

function getSavedVerifier() {
	return window.sessionStorage.getItem('code_verifier')
}

function getSavedState() {
	return window.sessionStorage.getItem('state')
}

const getAuthorizationCode = () => {
	const urlParams = new URLSearchParams(window.location.search)
	return urlParams.get('code')
}

const saveTokensToLocalStorage = (access_token, refresh_token) => {
	const tokens = { access_token, refresh_token }

	window.localStorage.setItem('token', JSON.stringify(tokens))
}
// === ! After redirect ! === //

export const authorize = () => {
	const codeVerifier = generateRandomString(128)
	const state = generateRandomString(128)

	saveStateAndVerifier(state, codeVerifier)
	try {
		generateCodeChallenge(codeVerifier)
			.then(codeChallenge => makeRedirectUrl(codeChallenge, state))
			.then(redirectUrl => {
				console.log(redirectUrl)
				window.location.replace(redirectUrl)
			})
	} catch (ex) {
		console.log(ex)
	}
}

export const getTokens = () => {
	const authorizationCode = getAuthorizationCode()

	const savedVerifier = getSavedVerifier()
	const savedState = getSavedState()

	const stateFromURL = new URLSearchParams(window.location.search).get('state')
	console.log(savedState)
	console.log(stateFromURL)
	if (savedState === stateFromURL) {
		console.log("We ARE HERE");
		console.log(
            "NEXT_PUBLIC_GP_AUTHSERVICE_CLIENT_SECRET: ",
            process.env.NEXT_PUBLIC_GP_AUTHSERVICE_CLIENT_SECRET
        );
		const response = axios.post(
            TOKEN_URL,
            queryString.stringify({
                grant_type: "authorization_code",
                client_id: Settings.keycloak.clientId,
                client_secret: process.env.NEXT_PUBLIC_GP_AUTHSERVICE_CLIENT_SECRET,
                redirect_uri: REDIRECT_URI,
                code_verifier: savedVerifier,
                code: authorizationCode,
                scope: "openid profile email",
            }),
            {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
            }
        );
		response
			.then(({ data }) => {
				console.log("We ARE NOT HERE")
				const access_token = data.access_token
				const refresh_token = data.refresh_token
				console.log("access token", access_token)
				console.log("refresh token", refresh_token)
				saveTokensToLocalStorage(access_token, refresh_token)

				window.localStorage.setItem('id_token', data.id_token)
				window.location.replace('/my')
			})
			.catch(console.error)
	} else {
		console.log('ERROR Стейты не совпадают')
	}
}
