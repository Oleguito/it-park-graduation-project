"use client";
import ProjectItem from "@/components/project-item/ProjectItem";
import { Button } from "@/components/ui/button";
import { Settings } from "@/constants/settings";
import { UserResponse } from "@/types/project/project";
import { UserQuery } from "@/types/UserQuery";
import { fetchUserInfo } from "@/utils/auth-service/user-service";
import { findUsersForProject } from "@/utils/project-service/project-service";
import axios from "axios";
import { useEffect, useState } from "react";



const ProjectsPage = () => {
    const [projects, setProjects] = useState([]);
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
            console.log("findUsersForProject response: ", response);
            setParticipants(response)
        })
        .catch(error => {
            console.log("findUsersForProject error: ", error);
        })

    }, []);

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
            <div>
                {projects
                    .filter((project) => {
                        // console.log(
                        //     `project.userId: ${project.ownerId} user.id: ${user.id}`
                        // );
                        return project.ownerId === user.id;
                    })
                    .map((project) => (
                        <>
                            <ProjectItem
                                key={project.id}
                                props={{
                                    id: project.id,
                                    title: project.name,
                                    status: project.status,
                                    description: project.description,
                                }}
                            />
                            <div>
                                <div className="project-item-participants-list">
                                    {
                                        <span className="font-bold">
                                            Участники:
                                        </span>
                                    }
                                    <div className="flex flex-wrap">
                                        {participants.map(participant => {
                                            console.log(participant)
                                            return (
                                                <span className="italic">
                                                    {participant.email}
                                                </span>
                                            );
                                        })}
                                    </div>
                                </div>
                                <div className="project-item-participants-list-buttons">
                                    <Button
                                        onClick={addMemberHandler}
                                        className="m-1"
                                    >
                                        Добавить участника
                                    </Button>
                                    <Button onClick={deleteMemberHandler}>
                                        Удалить участника
                                    </Button>
                                </div>
                            </div>
                        </>
                    ))}
            </div>
        </>
    );
};

export default ProjectsPage;
