# Collaborative Project Management Platform

This application provides a platform for teams to effectively plan, execute, and monitor the progress of their collaborative projects.

## Features

- Project planning and task management
- Integration with popular collaboration tools (calendars, document management, chat)
- Real-time project status tracking and reporting
- Customizable workflow and permissions management
- Mobile-responsive design for on-the-go access

## Getting Started

### Prerequisites

- Node.js (version 20.11.1 or higher)
- npm (version 10.8.2 or higher)

### Installation (tested on Windows)

1. Clone the repository:

   `git clone https://github.com/Oleguito/it-park-graduation-project.git`

2. Navigate to the project directory:

   `cd it-park-graduation-project`

3. Prepare containers

   ```
   cd infrastructure
   ./oleg-run.bat

4. Wait for 30-60 seconds

5. Start microservices, each in its own console window

   ```
   it-park-graduation-project/backend/auth-service/run.bat
   it-park-graduation-project/backend/chat-service/run.bat
   it-park-graduation-project/backend/document-service/run.bat
   it-park-graduation-project/backend/invitation-service/run.bat
   it-park-graduation-project/backend/notification-service/run.bat
   it-park-graduation-project/backend/project-service/run.bat```

6. Install the dependencies:

   ```
   cd it-park-graduation-project/frontend
   npm install

7. Start the development server:

   `npm run dev`

   The application should now be running at http://localhost:3000.

## Usage

1. Click "Выход" button to the right left. Sometimes you might need to reload the page. We are working on that.

2. Explore!

## Contributing

We welcome contributions to this project. If you have any ideas, bug reports, or feature requests, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).