'use client'
import { Card } from '@/components/ui/card'
import { createUserInBackend } from '@/utils/auth-service/user-service'
import { getTokens } from '@/utils/authorizationLogic'
import { useEffect } from 'react'

export default function Redirect() {
	useEffect(() => {
		getTokens()

		// console.log("before redirect");
		// (async () => await createUserInBackend())()
		// console.log("after redirect");

		
	}, [])
	return <Card>Загрузка...</Card>
}
