'use client'
import { Inter as FontSans } from 'next/font/google'
import './globals.css'

import { authorize } from '@/utils/authorizationLogic'
import { getTokens } from '@/utils/token'

const fontSans = FontSans({
	subsets: ['latin'],
	variable: '--font-sans',
})

export default function RootLayout() {
	const tokens = getTokens()

	if (
		tokens === undefined ||
		tokens === null ||
		Object.keys(tokens).length === 0
	)
		authorize()

	return <h1>Привет мир</h1>
}
