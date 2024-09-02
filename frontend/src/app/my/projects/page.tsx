"use client";
import ProjectItem from "@/components/project-item/ProjectItem";
import { Button } from "@/components/ui/button";
import { InvitationSearchResponse } from "@/types/invitation/invitation";
import { ProjectResponse, UserResponse } from "@/types/project/project";
import { UserQuery } from "@/types/UserQuery";
import { fetchUserInfo } from "@/utils/auth-service/user-service";
import { getInvitations } from "@/utils/invitation-service/invitation-service";
import {
	findUsersForProject,
	getProjectsFromBackend,
} from "@/utils/project-service/project-service";
import { useEffect, useState } from "react";

type MapProjectsAndUsers = {
    projectId: number;
    participants: UserResponse[];
    invitations?: InvitationSearchResponse[];
};

const ProjectsPage = () => {
    const [projects, setProjects] = useState([] as ProjectResponse[]);
    const [user, setUser] = useState({} as UserQuery);
    const [participants, setParticipants] = useState([] as UserResponse[]);
    const [projectsAndUsers, setProjectsAndUsers] = useState(
        [] as MapProjectsAndUsers[]
    );

    useEffect(() => {
        fetchUserInfo()
            .then((response) => {
                console.log("we are providing USER data: ", response[0]);
                setUser(response[0]);
            })
            .catch((error) => {
                console.log(`fetchUserInfo: ${error}`);
            });
    }, []); // Это вызовется только один раз при монтировании компонента

    useEffect(() => {
        if (user.email) {
            getProjectsFromBackend({ ownerEmail: user.email })
                .then((data: ProjectResponse[]) => {
                    setProjects(data);

                    // Массив обещаний для всех проектов
                    const projectPromises = data.map(
                        async (project: ProjectResponse) => {
                            try {
                                const participants = await findUsersForProject(
                                    project.id
                                );

                                const invitations = await getInvitations({
                                    emailFrom: user.email,
                                    projectId: project.id,
                                    status: "SENT",
                                }).catch(() => undefined); // если случилась ошибка, вернем undefined

                                return {
                                    projectId: project.id,
                                    participants,
                                    invitations,
                                };
                            } catch (error) {
                                console.error(
                                    `Ошибка при обработке проекта ${project.id}: `,
                                    error
                                );
                                return {
                                    projectId: project.id,
                                    participants: [],
                                    invitations: [],
                                };
                            }
                        }
                    );

                    // Ждем завершения всех обещаний и обновляем состояние
                    Promise.all(projectPromises)
                        .then((results: MapProjectsAndUsers[]) => {
                            setProjectsAndUsers(results);
                        })
                        .catch((error) => {
                            console.error(
                                "Ошибка при обработке проектов: ",
                                error
                            );
                        });
                })
                .catch((error) => {
                    console.log("getProjectsFromBackend: ", error);
                });
        }
    }, [user]);

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
                        console.log(project);
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
                                                    {projectsAndUsers
                                                        .filter((item) => {
                                                            return (
                                                                item.projectId ===
                                                                project.id
                                                            );
                                                        })
                                                        .map((item) => {
                                                            console.log(
                                                                "item: ",
                                                                item
                                                            );
                                                            return item.invitations?.map(
                                                                (
                                                                    invitation
                                                                ) => {
                                                                    return (
                                                                        <div
                                                                            key={
                                                                                invitation.invUUID
                                                                            }
                                                                        >
                                                                            {/* эта показывает поле КОМУ */}
                                                                            <div className="flex">
                                                                                <div className="w-1/5">
                                                                                    Кому:
                                                                                </div>
                                                                                <div className="italic">
                                                                                    {invitation.emailTo}
                                                                                </div>
                                                                            </div>
                                                                            {/* а эта - СТАТУС */}
                                                                            <div className="flex">
                                                                                <div className="w-1/5">
                                                                                    Статус:
                                                                                </div>
                                                                                <div className="italic">
                                                                                    {invitation.status}
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    );
                                                                }
                                                            );
                                                        })}
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
                                            <div>
                                                {projectsAndUsers
                                                    .filter((item) => {
                                                        console.log(
                                                            "item: ",
                                                            item
                                                        );
                                                        console.log(
                                                            "project: ",
                                                            project
                                                        );
                                                        return (
                                                            item.projectId ===
                                                            project.id
                                                        );
                                                    })
                                                    .map((item) => {
                                                        // console.log(participant)
                                                        return (
                                                            <span
                                                                className="italic mr-1"
                                                                key={
                                                                    item.projectId
                                                                }
                                                            >
                                                                {item.participants
                                                                    .map(
                                                                        (
                                                                            participant
                                                                        ) => {
                                                                            return participant.email;
                                                                        }
                                                                    )
                                                                    .join(", ")}
                                                            </span>
                                                        );
                                                    })}
                                            </div>
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
                        );
                    })}
            </div>
        </>
    );
};

export default ProjectsPage;
