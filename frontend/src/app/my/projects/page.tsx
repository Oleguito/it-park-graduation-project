'use client'
import ProjectItem from '@/components/project-item/ProjectItem';
import React from 'react'
import { Props } from "@/components/project-item/ProjectItem";
import { Button } from '@/components/ui/button';




const ProjectsPage = () => {

    const props: Props = {
        projectId: 2934804023,
        projectDescription: `Описание проекта \n Это страница с ПРОЕКТАМИ!!
▬ Сервис Управления Проектами
Функционал: Создание, редактирование и удаление проектов, управление задачами, назначение ответственных.
Технологии: gRPC, база данных для хранения информации о проектах и задачах (например, MongoDB).`,
    };

    const addMemberHandler = () => {
        window.location.href = "/my/participants/add"
    };

    const deleteMemberHandler = () => {
        window.location.href = "/my/participants/delete";
    };

  return (
      <>
          <div>Это страница с ПРОЕКТАМИ!!</div>
          <div>▬ Сервис Управления Проектами</div>
          <div>
              Функционал: Создание, редактирование и удаление проектов,
              управление задачами, назначение ответственных.
          </div>
          <div>
              Технологии: gRPC, база данных для хранения информации о проектах и
              задачах (например, MongoDB).
          </div>
          <div>
              <ProjectItem props={props} />
              <div className="project-item-lower-part">
                  <Button onClick={addMemberHandler} className='m-1'>Добавить участника</Button>
                  <Button onClick={deleteMemberHandler}>
                      Удалить участника
                  </Button>
              </div>
          </div>
      </>
  );
}

export default ProjectsPage;