"use client";
import ProjectItem from "@/components/project-item/ProjectItem";
import React, { useEffect, useState } from "react";
import { Props } from "@/components/project-item/ProjectItem";
import { Button } from "@/components/ui/button";
import MyButton from "@/stories/components/MyButton";
import css from "./page.module.css";
import { Settings } from "@/constants/settings";
import axios from "axios";

const ProjectsPage = () => {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        console.log("id_token: ", localStorage.getItem("id_token"));

        getProjectsFromBackend()
            .then((response) => {
                console.log("we are providing data");
                setProjects(response.data);
            })
            .catch((error) => {
                console.log(`getProjectsFromBackend: ${error}`);
            });
    }, []);

    const props: Props = {
        id: 2934804023,
        title: "Название проекта",
        status: "SOME STATUS",
        description: `Это мок проекта из фронтэнда \n 
        Описание проекта \n 
        Это страница с ПРОЕКТАМИ!!
        ▬ Сервис Управления Проектами
        Функционал: Создание, редактирование и удаление проектов, управление задачами, назначение ответственных.
        Технологии: gRPC, база данных для хранения информации о проектах и задачах (например, MongoDB).`,
    };

    const addMemberHandler = () => {
        window.location.href = "/my/participants/add";
    };

    const deleteMemberHandler = () => {
        window.location.href = "/my/participants/delete";
    };

    const getProjectsFromBackend = async () => {
        const response = await axios.get(
            Settings.backend.projectService.getAllProjectsUrl(),
            {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("id_token")}`,
                },
            }
        );
        console.log("response: ", response);
        return response;
    };

    console.log(
        `Settings: `,
        Settings.backend.projectService.getAllProjectsUrl()
    );
    console.log("projects: ", projects);

    return (
        <>
            <div>Это страница с ПРОЕКТАМИ!!</div>
            <div>▬ Сервис Управления Проектами</div>
            <div>
                Функционал: Создание, редактирование и удаление проектов,
                управление задачами, назначение ответственных.
            </div>
            <div>
                Технологии: gRPC, база данных для хранения информации о проектах
                и задачах (например, MongoDB).
            </div>
            <div>
                {projects.map((project) => (
                    <ProjectItem
                        key={project.id}
                        props={{
                            id: project.id,
                            title: project.name,
                            status: project.status,
                            description: project.description,
                        }}
                    />
                ))}

                <div className="project-item-lower-part">
                    <Button onClick={addMemberHandler} className="m-1">
                        Добавить участника
                    </Button>
                    <Button onClick={deleteMemberHandler}>
                        Удалить участника
                    </Button>
                </div>
            </div>
        </>
    );
};

export default ProjectsPage;
