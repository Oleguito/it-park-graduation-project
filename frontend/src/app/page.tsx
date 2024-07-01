'use client'
import { Inter as FontSans } from 'next/font/google'
import './globals.css'

import { authorize } from '@/utils/authorizationLogic'
import { getTokens } from '@/utils/token'
import { useEffect } from 'react'

const fontSans = FontSans({
	subsets: ['latin'],
	variable: '--font-sans',
})

export default function RootLayout() {
	

	useEffect(() => {

		// console.log("type of window RootLayout: ", typeof window)
		// console.log(window.localStorage)

		const tokens = getTokens();
		if (
            tokens === undefined ||
            tokens === null ||
            Object.keys(tokens).length === 0
        )
			authorize();

	}, [])
	

	return <h1>Привет мир</h1>
}
