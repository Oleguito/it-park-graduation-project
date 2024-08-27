"use client";

import { Button } from "@/components/ui/button";

const DocumentsPage = () => {

	const handleUploadDocument = () => {
		console.log("Uploading document to backend...")
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
                <Button
                    variant="outline"
                    onClick={() => {
                        handleUploadDocument();
                    }}
                >
                    <div>Загрузить документ</div>
                </Button>
            </div>
        </>
    );
};

export default DocumentsPage;
