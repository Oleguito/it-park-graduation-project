'use client'

import React, { useRef } from "react";
import { Button } from "../ui/button";
import { z } from "zod";
import { Project } from "@/types/project";
import axios from "axios";
import { Settings } from "@/constants/settings";


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

    async function sendProjectDataToBackend(projectData) {
        console.log("AddProjectBlock: sendProjectDataToBackend: ", projectData);

        const response = axios.post(
            Settings.backend.projectService.createProjectUrl(),
            projectData,
            {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("id_token")}`,
                },
            }
        )
        return response;
    }

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
            

            sendProjectDataToBackend({
                name: validatedData["project-title"],
                description: validatedData["project-description"],
                status: "NEW",
                due: validatedData["project-due"].toString(),
            })
            .then((response) => {
                console.log("response: ", response);
            })
            .catch((error) => {
                console.log("error: ", error);
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
            </div>
        </>
    );
};

export default AddProjectBlock;
