'use client';

import { Button } from '@/components/ui/button';
import { useRef } from 'react';
import { z } from "zod";
import css from './styles.module.css';

const schema = z.object({
    email_input_box: z
		.string()
		.email()
});

const AddParticipantPage = () => {

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
			emailRef.current.value = '';
		}
		console.log(emailRef.current.value);
		console.log(parsed);
	}

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
                        Добавить участника
                    </Button>
                </div>
            </div>
        </>
    );
};

export default AddParticipantPage;
