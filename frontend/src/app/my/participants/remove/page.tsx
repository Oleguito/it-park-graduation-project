"use client";


import { Button } from "@/components/ui/button";
import { Toaster } from "@/components/ui/toaster";
import { useToast } from "@/components/ui/use-toast";
import { removeParticipantFromProject } from "@/utils/project-service/project-service";
import { useSearchParams } from "next/navigation";
import { useRef } from "react";
import { z } from "zod";
import css from "./styles.module.css";


const schema = z.object({
    email_input_box: z.string().email(),
});

const DeleteParticipantPage = () => {
    
    const searchParams = useSearchParams();
    const { toast } = useToast();
    const emailRef = useRef(null);

    function handleRemoveParticipant(event): void {
        event.preventDefault();
        let parsed;
        try {
            parsed = schema.parse({
                email_input_box: emailRef.current.value,
            });
        } catch (error) {
            console.log(error);
            emailRef.current.value = "";
            toast({
                title: "Неправильный адрес электронной почты",
                description:
                    "Пожалуйста, введите правильный адрес электронной почты",
                duration: 3000,
                variant: "destructive",
            });
        }
        console.log(emailRef.current.value);
        console.log(parsed);
        const forProject = searchParams.get("forProject");
        console.log("forProject: ", forProject);

        removeParticipantFromProject({
            email: emailRef.current.value,
            projectId: Number(forProject),
        })
            .then((response) => {
                console.log("removeParticipantToProject SUCCESS!");
                toast({
                    title: "Участник удален!",
                    description: "Участник удален",
                    duration: 3000,
                    variant: "default",
                });
            })
            .catch((error) => {
                console.log("removeParticipantToProject error: ", error);
                toast({
                    title: "Произошла ошибка!",
                    description: "Участник НЕ удален",
                    duration: 3000,
                    variant: "default",
                });
            });
    }
    

	return (
        <>
            <div>RemoveParticipantPage</div>

            <div className={`w-full`}>
                <p>Введите адрес электронной почты участника</p>
                <div className="flex">
                    <label htmlFor="project-title">Email:</label>
                    <input
                        ref={emailRef}
                        type="text"
                        id="email_input_box"
                        name="email_input_box"
                        placeholder="Адрес электронной почты"
                        className={css.email_input_box}
                    ></input>
                </div>
                <div className="flex justify-center">
                    <Button
                        onClick={handleRemoveParticipant}
                        className="m-1 bg-slate-500 text-white font-bold rounded-sm"
                    >
                        Удалить участника
                    </Button>
                </div>
            </div>
            <Toaster />
        </>
    );
};

export default DeleteParticipantPage;

/*
sex@bomb.com
*/ 