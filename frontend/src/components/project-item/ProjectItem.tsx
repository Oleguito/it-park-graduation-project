'use client'


import { InvitationSearchResponse } from '@/types/invitation/invitation';
import { ProjectResponse } from '@/types/project/project';
import { getProjectsFromBackend } from '@/utils/project-service/project-service';
import { useEffect, useState } from 'react';
import styles from './styles.module.css';

const ProjectItem = ({ props }: { props: InvitationSearchResponse & ProjectResponse }) => {

    const [project, setProject] = useState({} as ProjectResponse)

    useEffect(() => {
        getProjectsFromBackend({
            projectId: props.projectId,
        })
            .then((response: ProjectResponse[]) => {
                response.length > 0 ? setProject(response[0]) : null
            })
            .catch(error => console.log("Error occuring while fetching Project info: ", error))

    }, [props.projectId])

    return (
        <>
            <div>
                <div>ProjectItem</div>
                <div>
                    <div className="font-bold w-1/5 inline-block">Title:</div>
                    <div className="italic inline-block">{project.name}</div>
                </div>
                <div>
                    <div className="font-bold w-1/5 inline-block">Status:</div>
                    <div className="inline-block">{project.status}</div>
                </div>
                <div className={styles["project-item-upper-part"]}>
                    <div className="font-bold w-1/4">
                        Project id: {project.id}
                    </div>
                    <textarea
                        value={project.description}
                        className={`${styles["description-box"]} italic`}
                        readOnly={true}
                    />
                </div>
            </div>
        </>
    );
}

export default ProjectItem