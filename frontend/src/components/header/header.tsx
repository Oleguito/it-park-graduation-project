'use client'

import { Button } from '@/components/ui/button'
import { ModeToggle } from '@/components/ui/modeToggle'
import { signOut } from '@/utils/token'
import Link from 'next/link'
import { usePathname } from 'next/navigation'
import {isRouteActive} from '@/utils/utilities/utilities'



export const Header = () => {
	const pathName = usePathname()

	return (
		<header className='flex justify-between sticky w-full p-4'>
			<ModeToggle />
			<div className='flex gap-5'>
				<Link href={'/my'}>
					<Button
						variant={isRouteActive(pathName, '/my') ? 'outline' : 'ghost'}
					>
						Личный кабинет
					</Button>
				</Link>
				<Button onClick={signOut}>Выход</Button>
			</div>
		</header>
	)
}
