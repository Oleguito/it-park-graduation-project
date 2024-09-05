'use client'

import { useToast } from "@/components/ui/use-toast";
import { UserQuery } from "@/types/UserQuery";
import { fetchUserInfo } from "@/utils/auth-service/user-service";
import { createProjectOnBackend } from "@/utils/project-service/project-service";
import React, { useEffect, useRef, useState } from "react";
import { z } from "zod";
import { Button } from "../ui/button";
import { Toaster } from "../ui/toaster";


const projectSchema = z.object({
    "project-title": z
        .string()
        .min(3, "Название проекта должно быть длиннее 3 символов"),
    "project-description": z
        .string()
        .min(10, "Описание проекта должно быть длиннее 10 символов"),
    "project-due": z
        .date()
        .min(new Date(), "Срок выполнения проекта не может быть в прошлом"),
});

const AddProjectBlock = () => {

    const formdata = useRef(null);
    const { toast } = useToast();
    const [user, setUser] = useState({} as UserQuery);


    useEffect(() => {
        // console.log("id_token: ", localStorage.getItem("id_token"));

        fetchUserInfo()
            .then((response) => {
                console.log("we are providing USER data: ", response[0]);
                console.log("length: ", response.length);
                setUser(response[0]);
                console.log(`userID: ${user}`, user);
            })
            .catch((error) => {
                console.log(`fetchUserInfo: ${error}`);
            });
    }, []);

    function submitHandler(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        console.log(
            "AddProjectBlock: submitHandler: submitting form data to backend"
        );
        const formData = new FormData(e.currentTarget);
        const formValues = Object.fromEntries(formData.entries());

        // console.log("formValues: ", formValues);
        // console.log("formdata: ", formdata.current);

        try {
            const validatedData = projectSchema.parse({
                "project-title": formValues["project-title"],
                "project-description": formValues["project-description"],
                "project-due": new Date(formValues["project-due"].toString()),
            });
            
            console.log("Validated data:", validatedData);
            

            createProjectOnBackend({
                name: validatedData["project-title"],
                description: validatedData["project-description"],
                startDate: new Date(),
                endDate: validatedData["project-due"],
                status: "NEW",
                ownerEmail: user.email,
                dateInfo: {
                    createdAt: new Date(),
                    deletedAt: null,
                },
                creatorEmail: user.email,
            })
            .then((response) => {
                console.log("response: ", response);
                toast({
                  title: "Проект успешно создан",
                  description: "Проект успешно создан",
                  duration: 3000,
                  variant: "default",
                });
            })
            .catch((error) => {
                console.log("error: ", error);
                toast({
                    title: "Произошла ошибка!",
                    description: error.response.data.message,
                    duration: 3000,
                    variant: "default",
                })
            });
            
            

        } catch (error) {
            if (error instanceof z.ZodError) {
                console.error("Validation error:", error.issues);
            } else {
                console.error("Unexpected error:", error);
            }
        }
    }

    return (
        <>
            <div>AddProjectBlock</div>
            <div>Это блок для добавления проектов</div>
            <div className="container">
                <form onSubmit={submitHandler} ref={formdata}>
                    <div>
                        <label htmlFor="project-title">Название проекта:</label>
                        <input
                            type="text"
                            id="project-title"
                            name="project-title"
                            placeholder="Название проекта"
                        ></input>
                    </div>
                    <div className="flex">
                        <label htmlFor="project-description">
                            Описание проекта:
                        </label>
                        <textarea
                            id="project-description"
                            name="project-description"
                            placeholder="Описание проекта"
                            cols={80}
                        ></textarea>
                    </div>
                    <div>
                        <label htmlFor="project-due">
                            Срок выполнения проекта:
                        </label>
                        <input type="date" id="project-due" name="project-due"></input>
                    </div>
                    <Button>Добавить проект</Button>
                </form>
                <Toaster/>
            </div>
        </>
    );
};

export default AddProjectBlock;
