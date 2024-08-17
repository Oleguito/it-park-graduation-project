'use client';

import { Button } from '@/components/ui/button';
import css from './styles.module.css';

const AddParticipantPage = () => {

	const handleAddParticipant = () => {
		
	}

    return (
        <>
            <div>AddParticipantPage</div>

            <div className={`w-full`}>
                <p>Введите адрес электронной почты участника</p>
                <div className="flex">
                    <label htmlFor="project-title">Email:</label>
                    <input
                        type="text"
                        id="email_input_box"
                        name="email_input_box"
                        placeholder="Адрес электронной почты"
                        className={css.email_input_box}
                    ></input>
                </div>
                <div className='flex justify-center'>
                    <Button onClick={handleAddParticipant} className="m-1 bg-slate-500 text-white font-bold rounded-sm">
                        Добавить участника
                    </Button>
                </div>
            </div>
        </>
    );
};

export default AddParticipantPage;
