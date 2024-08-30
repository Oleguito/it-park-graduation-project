"use client";

import { Button } from "@/components/ui/button";
import { Toaster } from "@/components/ui/toaster";
import { useToast } from "@/components/ui/use-toast";
import { sendNotification } from "@/utils/project-service/project-controller";
import { useSearchParams } from "next/navigation";
import { useRef } from "react";
import { z } from "zod";
import css from "./styles.module.css";

// free@sex.com

const schema = z.object({
    email_input_box: z.string().email(),
});

const AddParticipantPage = () => {

    const searchParams = useSearchParams();
    const { toast } = useToast();
    const emailRef = useRef(null);

    const handleAddParticipant = (event) => {
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
				description: "Пожалуйста, введите правильный адрес электронной почты",
				duration: 3000,
				variant: "destructive",
			})
        }
        console.log(emailRef.current.value);
        console.log(parsed);
        
        const forProject = searchParams.get('forProject');
        console.log("forProject: ", forProject);

        sendNotification({
            projectCreatorEmail: "tenzoriator@rambler.ru",
            invitedUserEmail: emailRef.current.value,
            invitationMessage: "ХЕЛО МАЗАФАКА",
            type: "invite",
            key: "9b5e159b-fac5-42e2-ae45-4c91e90ec8c3",
            projectId: Number(forProject),
            projectTitle: "специально не записал имя",
            projectStatus: "специально не записал статус",
            projectDescription: "специально не записал описание",
        }).then(response => {
            toast(
                {
                    title: "Участник приглашен",
                    description: "Оповещение участнику отправлено",
                    duration: 3000,
                    variant: "default",
                }
            )
        })
        .catch (error => {
            console.log("handleAddParticipant error: ", error);
            toast({
                title: "Произошла ошибка!",
                description: error.response.data.message,
                duration: 3000,
                variant: "default",
            });
        });
        
        // addParticipantToProject({
        //     email: emailRef.current.value,
        //     projectId: Number(forProject),
        // })
        // .then(response => {
        //     console.log("handleAddParticipant SUCCESS!");
        //     toast({
        //         title: "Участник добавлен",
        //         description: "Участник добавлен",
        //         duration: 3000,
        //         variant: "default",
        //     })
        // }).catch (error => {
        //     console.log("handleAddParticipant error: ", error);
        //     toast({
        //         title: "Произошла ошибка!",
        //         description: "Участник НЕ добавлен",
        //         duration: 3000,
        //         variant: "default",
        //     });
        // });
    };

    return (
        <>
            <div>AddParticipantPage</div>

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
                        onClick={handleAddParticipant}
                        className="m-1 bg-slate-500 text-white font-bold rounded-sm"
                    >
                        Отправить приглашение
                    </Button>
                </div>
            </div>
            <Toaster />
        </>
    );
};

export default AddParticipantPage;
