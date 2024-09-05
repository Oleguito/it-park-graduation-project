import { FilesTable } from '@/components/files/FilesTable'

export const ProjectFiles = ({ params }: { params: { project_id: number } }) => {
  return (
    <div>
        <FilesTable projectId={params.project_id} updateFiles={false}/>
    </div>
  )
}

export default ProjectFiles
