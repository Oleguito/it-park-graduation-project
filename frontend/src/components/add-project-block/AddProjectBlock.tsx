'use client'

import React from "react";
import { Button } from "../ui/button";
import { z } from "zod";


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

    function submitHandler(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        console.log(
            "AddProjectBlock: submitHandler: submitting form data to backend"
        );
        const formData = new FormData(e.currentTarget);
        const formValues = Object.fromEntries(formData.entries());
        
        try {
            const validatedData = projectSchema.parse(formValues);
            console.log("Validated data:", validatedData);
            


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
                <form onSubmit={submitHandler}>
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
                        <label htmlFor="project-description">Описание проекта:</label>
                        <textarea
                            placeholder="Описание проекта"
                            cols={80}
                        ></textarea>
                    </div>
                    <div>
                        <label htmlFor="project-due">Срок выполнения проекта:</label>
                        <input type="date" id="project-due"></input>
                    </div>
                    <Button>Добавить проект</Button>
                </form>
            </div>
        </>
    );
};

export default AddProjectBlock;
