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
              <span>
                  Title: <span className="italic">{props.title}</span>
              </span>
              <div>Status: <span>{props.status}</span></div>
              <div className={css["project-item-upper-part"]}>
                  <div>Project id: {props.id}</div>
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