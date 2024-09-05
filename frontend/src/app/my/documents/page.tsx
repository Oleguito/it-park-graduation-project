"use client";

import FileUploadComponent from "@/components/file-upload/FileUploadComponent";
import { Button } from "@/components/ui/button";
import axios from "axios";

const DocumentsPage = () => {

    const downloadFile = () => {
        axios.get("http://localhost:8080/api/files/download/sdgfsdf", {
            
        })
    }

	return (
        <>
            <div>DocumentsPage</div>
            <div>
                ▬ Сервис Управления Документами Функционал: Загрузка, хранение и
                совместная работа с документами, управление доступом к
                документам. Технологии: Облачное хранилище (например, AWS S3),
                редакторы документов (например, Google Docs API).
            </div>
            <div className="w-full">
                <div>Загруженные документы:</div>
                <div>
                    <ul>
                        <li>Документ1.pdf</li>
                        <li>Документ2.png</li>
                        <li>Документ3.excalidraw</li>
                    </ul>
                </div>
				<FileUploadComponent />
            </div>

            <Button
                onClick={() => {
                    downloadFile();
                }}
            >Скачать документ</Button>
        </>
    );
};

export default DocumentsPage;
