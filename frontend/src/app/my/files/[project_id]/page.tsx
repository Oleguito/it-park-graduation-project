import React from 'react'
import { FilesTable } from '@/components/files/FilesTable'
import { EXPORT_DETAIL } from 'next/dist/shared/lib/constants'

export const ProjectFiles = ({ params }: { params: { project_id: number } }) => {
  return (
    <div>
        <FilesTable projectId={params.project_id}/>
    </div>
  )
}

export default ProjectFiles
