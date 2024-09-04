'use client'

import Link from 'next/link'
import React from 'react'
import { TableRow, TableCell } from '../ui/table'
import { FileAttachmentArray, FileAttachment } from '@/types/document/document'

export const FileElement = (props: {
  elem: FileAttachment,
}) => {
    return (
      <div key={props.elem.link}>
        <TableRow>
          <TableCell className="font-medium">{props.elem.fileName}</TableCell>
          <TableCell>{props.elem.userId}</TableCell>
          <TableCell>{props.elem.projectId}</TableCell>
          <TableCell className="text-right">
            <Link href={props.elem.link} passHref legacyBehavior>
              <a target="_blank" rel="noopener noreferrer">Скачать</a>
            </Link>
          </TableCell>
          <TableCell className="text-right">
            <Link href="https://www.example.com" passHref legacyBehavior>
              <a target="_blank" rel="noopener noreferrer">Удалить</a>
            </Link>
          </TableCell>
        </TableRow>
      </div>
      )

}
