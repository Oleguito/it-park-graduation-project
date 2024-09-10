'use client'

import { FileAttachment } from '@/types/document/document'
import { deleteFile } from '@/utils/document-service/document-service'
import Link from 'next/link'
import { TableCell, TableRow } from '../ui/table'

export const FileElement = (props: {
  elem: FileAttachment,
  callback: () => void
}) => {

    const handleDeleteFile = () => {
      console.log("deleting file: ", props.elem);
      deleteFile(props.elem.userId, props.elem.projectId, props.elem.fileName)
      .then((response) => {
        console.log(response);
        props.callback();
      })
      .catch((error) => {
        console.log("Error while deleting file: ", error);
      });
    }

    return (
      <TableRow key={props.elem.link}>
        <TableCell className="font-medium">{props.elem.fileName}</TableCell>
        <TableCell>{props.elem.userId}</TableCell>
        <TableCell>{props.elem.projectId}</TableCell>
        <TableCell className="text-right">
          <Link href={props.elem.link} passHref legacyBehavior>
            <a target="_blank" rel="noopener noreferrer">
              Скачать
            </a>
          </Link>
        </TableCell>
        <TableCell className="text-right">
          <Link href={''}>
            <div onClick={handleDeleteFile}>
              Удалить
            </div>
          </Link>
        </TableCell>
      </TableRow>
    );

}
