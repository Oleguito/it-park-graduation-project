'use client'


import css from './styles.module.css';

export type Props = {
    id: number;
    title: string;
    description: string;
    status: String
}

const addMemberHandler = () => {};

const deleteMemberHandler = () => {};

const ProjectItem = ({ props }: { props: Props }) => {
  return (
      <>
          <div>
              <div>ProjectItem</div>
              <div>
                  <span className="font-bold">Title:</span>
                  <span className="italic">{props.title}</span>
              </div>
              <div>
                  <span className="font-bold">Status:</span>
                  <span>{props.status}</span>
              </div>
              <div className={css["project-item-upper-part"]}>
                  <div className="font-bold w-1/5">Project id: {props.id}</div>
                  <textarea
                      value={props.description}
                      className={`${css["description-box"]} italic`}
                      readOnly={true}
                  />
              </div>
          </div>
      </>
  );
}

export default ProjectItem