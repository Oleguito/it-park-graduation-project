'use client'

import React from 'react'
import { Button } from '../ui/button';

import css from './styles.module.css'

export type Props = {

    projectId: number;
    projectDescription: string;
}

const addMemberHandler = () => {};

const deleteMemberHandler = () => {};

const ProjectItem = ({ props }: { props: Props }) => {
  return (
      <>
          <div>
              <div>ProjectItem</div>
              <div className={css["project-item-upper-part"]}>
                  <div>Project #{props.projectId}</div>
                  <textarea
                      value={props.projectDescription}
                      className={css["description-box"]}
                      readOnly={true}
                  />
              </div>
          </div>
      </>
  );
}

export default ProjectItem