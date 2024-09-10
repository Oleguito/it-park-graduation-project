"use client";

import { FileElement } from "@/components/files/FileRow";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import {
  Table,
  TableBody,
  TableCaption,
  TableHead,
  TableHeader,
  TableRow
} from "@/components/ui/table";
import { Settings } from "@/constants/settings";
import { FileAttachmentArray } from "@/types/document/document";
import { downloadListFiles } from "@/utils/document-service/document-service";
import { useEffect, useState } from "react";
import { Button } from "../ui/button";

export const FilesTable = (props: { projectId: number; updateFiles: boolean;}) => {
  const [files, setFiles] = useState([] as FileAttachmentArray);
  const [update, setUpdate] = useState(false);

  useEffect(() => {
    console.log("project id in Table: ", props.projectId);
    let url: string = Settings.backend.documentService.downloadListFiles();

    downloadListFiles(url, {
      projectId: props.projectId,
    })
      .then((response) => {
        console.log("Files from Minio: ", response);
        setFiles(response);
      })
      .catch((error) =>
        console.log("Error occuring while fetching list of files: ", error),
      );
  }, [props.projectId, props.updateFiles, update]);

  return (
    <div>
      <Dialog>
        <DialogTrigger asChild>
          <Button className={"from-neutral-100 bg-indigo-400 rounded"}>
            Показать вложения
          </Button>
        </DialogTrigger>
        <DialogContent className="max-w-[80%] w-[80%]">
          <DialogHeader>
            <DialogTitle className="text-[30px]">
              Прикрепленные файлы
            </DialogTitle>
          </DialogHeader>
          <DialogDescription></DialogDescription>
          <Table>
            <TableCaption>Список добавленных Вами файлов</TableCaption>
            <TableHeader>
              <TableRow>
                <TableHead className="w-[100px]">Имя вложения</TableHead>
                <TableHead>ID автора</TableHead>
                <TableHead className="">ID проекта</TableHead>
                <TableHead className="text-right"></TableHead>
                <TableHead className="text-right"></TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {files.length > 0 &&
                files.map((file) => {
                  return <FileElement key={props.projectId} elem={file} callback={() => setUpdate(!update)} />;
                })}
            </TableBody>
          </Table>
        </DialogContent>
      </Dialog>
    </div>
  );
};
