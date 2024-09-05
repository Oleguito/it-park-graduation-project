"use client";

import React, { useEffect, useState } from "react";
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
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import Link from "next/link";
import { Settings } from "@/constants/settings";
import { downloadListFiles, uploadFile } from "@/utils/document-service/document-service";
import { FileAttachmentArray } from "@/types/document/document";
import { error } from "console";
import { FileElement } from "@/components/files/FileRow";

export const FilesTable = (props: { projectId: number; updateFiles: boolean }) => {
  const [files, setFiles] = useState([] as FileAttachmentArray);

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
  }, [props.projectId, props.updateFiles]);

  return (
    <div>
      <Dialog>
        <DialogTrigger asChild>
          <button className={"from-neutral-100"}>Показать вложения</button>
        </DialogTrigger>
        <DialogContent className="max-w-[60rem] w-[60rem]">
          <DialogHeader>
            <DialogTitle className="text-[30px]">
              Прикрепленные файлы
            </DialogTitle>
          </DialogHeader>
          <DialogDescription></DialogDescription>
          <Table>
            <TableCaption>A list of your recent invoices.</TableCaption>
            <TableHeader>
              <TableRow>
                <TableHead className="w-[100px]">Имя вложения</TableHead>
                <TableHead>Автор</TableHead>
                <TableHead className="hidden">ИД проекта</TableHead>
                <TableHead className="text-right"></TableHead>
                <TableHead className="text-right"></TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {files.length > 0 &&
                files.map((file) => {
                  return <FileElement key={props.projectId} elem={file} />;
                })}
            </TableBody>
          </Table>
        </DialogContent>
      </Dialog>
    </div>
  );
};
