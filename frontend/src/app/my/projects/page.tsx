"use client";
import ProjectItem from "@/components/project-item/ProjectItem";
import { Button } from "@/components/ui/button";
import { Settings } from "@/constants/settings";
import { ProjectResponse, UserResponse } from "@/types/project/project";
import { ProjectSearchQuery } from "@/types/project/projectsearchquery";
import { UserQuery } from "@/types/UserQuery";
import { fetchUserInfo } from "@/utils/auth-service/user-service";
import { findUsersForProject, getProjectsFromBackend } from "@/utils/project-service/project-service";
import axios from "axios";
import { useEffect, useState } from "react";

type MapProjectsAndUsers = {
    projectId: number,
    participants: UserResponse[]
}

const ProjectsPage = () => {
    const [projects, setProjects] = useState([] as ProjectResponse[]);
    const [user, setUser] = useState({} as UserQuery);
    const [participants, setParticipants] = useState([] as UserResponse[]);
    const projectsAndUsers: MapProjectsAndUsers[] = []

    useEffect(() => {
        // console.log("id_token: ", localStorage.getItem("id_token"));

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

        let query: ProjectSearchQuery = {
            ownerEmail: user.email,
        }

        getProjectsFromBackend(query)
            .then((data: ProjectResponse[]) => {
                setProjects(data)
                // console.log("Fetched data: ", projects)
                data.map((project: ProjectResponse) => {
                    console.log("BEFORE FETCHIND PROJECT USERS INFO")
                    findUsersForProject(project.id)
                        .then(response => {
                            console.log("Participants response: ", response)
        
                            projectsAndUsers.push({ projectId: project.id, participants: response })
                            console.log("USERS: ", projectsAndUsers)
                            setParticipants(response)
                        })
                        .catch(error => {
                            console.log("findUsersForProject error: ", error);
                        })
                })
            })
            .catch(error => {
                console.log("getProjectsFromBackend: ", error)
            })

        }, []);

    const addMemberHandler = (project: ProjectResponse) => {
        window.location.href = `/my/participants/add?forProject=${project.id}`;
    };

    const deleteMemberHandler = (project: ProjectResponse) => {
        window.location.href = `/my/participants/remove?forProject=${project.id}`;
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
                        return project.ownerEmail === user.email;
                    })
                    .map((project) => {

                        return (
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
                                            {projects.map((participant) => {
                                                // console.log(participant)
                                                return (
                                                    <span
                                                        className="italic mr-1"
                                                        key={participant.ownerEmail}
                                                    >
                                                        {participant.ownerEmail}
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
                        )
                    })}
            </div>
        </>
    );
};

export default ProjectsPage;
