"use client";
import ProjectItem from "@/components/project-item/ProjectItem";
import { Button } from "@/components/ui/button";
import { Settings } from "@/constants/settings";
import { ProjectResponse, UserResponse } from "@/types/project/project";
import { UserQuery } from "@/types/UserQuery";
import { fetchUserInfo } from "@/utils/auth-service/user-service";
import { findUsersForProject } from "@/utils/project-service/project-service";
import axios from "axios";
import { useEffect, useState } from "react";



const ProjectsPage = () => {
    const [projects, setProjects] = useState([] as ProjectResponse[]);
    const [user, setUser] = useState({} as UserQuery);
    const [participants, setParticipants] = useState([] as UserResponse[]);

    useEffect(() => {
        // console.log("id_token: ", localStorage.getItem("id_token"));

        getProjectsFromBackend()
        .then((response) => {
            // console.log("we are providing PROJECTS data", response.data);
            setProjects(response.data);
        })
        .catch((error) => {
            console.log(`getProjectsFromBackend: ${error}`);
        });
        
        fetchUserInfo()
        .then((response) => {
            // console.log("we are providing USER data: ", response[0]);
            // console.log("length: ", response.length);
            setUser(response[0]);
            // console.log(`userID: ${user}`, user)
        })
        .catch((error) => {
            console.log(`fetchUserInfo: ${error}`);
        });

        findUsersForProject(1)
        .then(response => {
            // console.log("findUsersForProject response: ", response);
            setParticipants(response)
        })
        .catch(error => {
            console.log("findUsersForProject error: ", error);
        })

    }, []);

    const addMemberHandler = (project: ProjectResponse) => {
        window.location.href = `/my/participants/add?forProject=${project.id}`;
    };

    const deleteMemberHandler = (project: ProjectResponse) => {
        window.location.href = `/my/participants/remove?forProject=${project.id}`;
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
        // console.log("response: ", response);
        return response;
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
                Технологии: gRPC, база данных для хранения информации о проектах
                и задачах (например, MongoDB).
            </div>
            <div className="w-full">
                {projects
                    .filter((project) => {
                        // console.log(
                        //     `project.userId: ${project.ownerId} user.id: ${user.id}`
                        // );
                        return project.ownerId === user.id;
                    })
                    .map((project) => (
                        <div key={project.id}>
                            <ProjectItem
                                props={{
                                    id: project.id,
                                    title: project.name,
                                    status: project.status,
                                    description: project.description,
                                }}
                            />
                            <div key={project.id * 2}>
                                <div>
                                    <div className="font-bold">
                                        Приглашения от Вас в данный проект:
                                    </div>
                                    <div>
                                        <ul>
                                            <li>
                                                {/* эта дивка показывает одно приглашение */}
                                                <div>
                                                    {/* эта показывает поле КОМУ */}
                                                    <div className="flex">
                                                        <div className="w-1/5">
                                                            Кому:
                                                        </div>
                                                        <div className="italic">
                                                            vasya123@gmail.com
                                                        </div>
                                                    </div>
                                                    {/* а эта - СТАТУС */}
                                                    <div className="flex">
                                                        <div className="w-1/5">
                                                            Статус:
                                                        </div>
                                                        <div className="italic">
                                                            SENT
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div className="project-item-participants-list">
                                    {
                                        <div>
                                            <span className="font-bold">
                                                Участники уже принявшие Ваше
                                                приглашение:
                                            </span>
                                            <p>(Включая Вас)</p>
                                        </div>
                                    }
                                    <div className="flex flex-wrap">
                                        {participants.map((participant) => {
                                            // console.log(participant)
                                            return (
                                                <span
                                                    className="italic mr-1"
                                                    key={participant.email}
                                                >
                                                    {participant.email}
                                                </span>
                                            );
                                        })}
                                    </div>
                                </div>
                                <div className="project-item-participants-list-buttons">
                                    <p>Отправить приглашение:</p>
                                    <Button
                                        onClick={() => {
                                            addMemberHandler(project);
                                        }}
                                        className="m-1"
                                    >
                                        Добавить участника
                                    </Button>
                                    <p>
                                        Удалить участника, принявшего
                                        приглашение:
                                    </p>
                                    <Button
                                        onClick={() => {
                                            deleteMemberHandler(project);
                                        }}
                                    >
                                        Удалить участника
                                    </Button>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
        </>
    );
};

export default ProjectsPage;
