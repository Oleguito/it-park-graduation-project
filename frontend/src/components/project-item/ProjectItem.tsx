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
                  <div className="font-bold w-1/5 inline-block">Title:</div>
                  <div className="italic inline-block">{props.title}</div>
              </div>
              <div>
                  <div className="font-bold w-1/5 inline-block">Status:</div>
                  <div className="inline-block">{props.status}</div>
              </div>
              <div className={css["project-item-upper-part"]}>
                  <div className="font-bold w-1/4">
                      Project id: {props.id}
                  </div>
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