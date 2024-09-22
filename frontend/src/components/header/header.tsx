'use client'

import { Button } from '@/components/ui/button'
import { ModeToggle } from '@/components/ui/modeToggle'
import { signOut } from '@/utils/token'
import { isRouteActive } from '@/utils/utilities/utilities'
import Link from 'next/link'
import { usePathname } from 'next/navigation'

const addProjectHandler = () => {
    window.location.href = "/my/projects/add"
};

const myProjectsHandler = () => {
    window.location.href = "/my/projects"
};

const invitationsHandler = () => {
    window.location.href = "/my/projects/invitations"
};

export const Header = () => {
	const pathName = usePathname()

	return (
    <header className="flex justify-between sticky w-full p-4">
      <ModeToggle />
      <div className="flex gap-5">
        {/* <Button onClick={addProjectHandler}>Добавить проект</Button>
        <Button onClick={myProjectsHandler}>Мои проекты</Button>
        <Button onClick={invitationsHandler}>Приглашения</Button> */}
        <Link href={"/my"}>
          <Button
            variant={isRouteActive(pathName, "/my") ? "default" : "default"}
          >
            Личный кабинет
          </Button>
        </Link>
        <Button onClick={signOut}>Выход</Button>
      </div>
    </header>
  );
}
