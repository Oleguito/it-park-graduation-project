//'use client'
import ProjectItem from '@/components/project-item/ProjectItem';
import React from 'react'
import { Props } from "@/components/project-item/ProjectItem";



const ProjectsPage = () => {

    const props: Props = {
        projectId: 2934804023,
        projectDescription: `Описание проекта \n Это страница с ПРОЕКТАМИ!!
▬ Сервис Управления Проектами
Функционал: Создание, редактирование и удаление проектов, управление задачами, назначение ответственных.
Технологии: gRPC, база данных для хранения информации о проектах и задачах (например, MongoDB).`,
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
          <ProjectItem props={props} />
      </>
  );
}

export default ProjectsPage;