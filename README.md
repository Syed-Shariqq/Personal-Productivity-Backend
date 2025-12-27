# Personal Productivity Backend

A Spring Boot backend for a single-user personal document & productivity management system. This application provides a comprehensive platform for managing notes, media files, email scheduling, QR code generation, PDF handling, and search history.

## 🎯 Features

- **Notes Management**: Create, update, and organize personal notes with folder-based organization
- **Media Upload**: Upload and manage media files with secure storage
- **Folder Organization**: Hierarchical folder structure for organizing documents and notes
- **Email Scheduler**: Schedule and send automated emails
- **QR Code Generation**: Generate QR codes for sharing and tracking
- **PDF Processing**: Convert documents to PDF format
- **Search History**: Track and retrieve search history
- **Authentication**: Spring Security Basic Authentication
- **CORS Support**: Configured for single-user access with proper security headers

## 🛠 Tech Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: MySQL 8.0+
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security (HTTP Basic) for single-user access
- **Build Tool**: Maven
- **API Documentation**: Swagger/Springdoc-OpenAPI (if configured)

## 📁 Project Structure

```
src/main/java/com/example/demo/
├── MyDoc.java                          # Main Spring Boot application
├── confige/
│   ├── CorsConfig.java                 # CORS configuration
│   └── SpringSecurity.java             # Spring Security configuration
├── controller/
│   ├── AuthController.java             # Authentication endpoints
│   ├── EmailController.java            # Email scheduling endpoints
│   ├── FolderController.java           # Folder management endpoints
│   ├── MediaController.java            # Media upload/management endpoints
│   ├── NoteController.java             # Note management endpoints
│   ├── PdfController.java              # PDF processing endpoints
│   ├── QRcodeController.java           # QR code generation endpoints
│   └── SearchHistoryController.java    # Search history endpoints
├── entity/
│   ├── EmailRequest.java               # Email request entity
│   ├── Folder.java                     # Folder entity
│   ├── Media.java                      # Media file entity
│   ├── Note.java                       # Note entity
│   └── SearchHistory.java              # Search history entity
├── repository/
│   ├── FolderRepository.java           # Folder data access
│   ├── MediaRepository.java            # Media data access
│   ├── NoteRepository.java             # Note data access
│   └── SearchHistoryRepository.java    # Search history data access
├── scheduler/
│   └── EmailScheduler.java             # Email scheduling tasks
└── service/
    ├── EmailService.java               # Email business logic
    ├── FolderService.java              # Folder business logic
    ├── MediaService.java               # Media business logic
    ├── NoteService.java                # Note business logic
    ├── PdfService.java                 # PDF processing logic
    ├── QRcodeService.java              # QR code generation logic
    └── SearchHistoryService.java       # Search history logic
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8.0 or higher
- MySQL 8.0 or higher
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Syed-Shariqq/Personal-Productivity-Backend.git
   cd Personal-Productivity-Backend
   ```

2. **Configure the database**
   - Create a MySQL database for the application
   - Update database credentials in `demo/src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Configure application settings**
   - Update any additional settings in `application.properties`:
     - Basic authentication credentials
     - Email scheduler settings
     - Upload directory path
     - Other business logic configurations


4. **Build the project**
   ```bash
   cd demo
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080` (or configured port)

## 📝 Configuration

### Key Application Properties

Create/Update `demo/src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/personal_productivity
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Security (Basic Auth)
spring.security.user.name=your_username
spring.security.user.password=your_password

# Email Configuration (if using Email Scheduler)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# File Upload
file.upload.dir=./uploads
```

## 🔒 Security Notes

### Uploads Folder

- **Location**: The `uploads/` folder stores user-uploaded media files
- **Security**: This folder should NOT be committed to version control (included in .gitignore)
- **Access**: Implement proper access controls in the MediaController to ensure users can only access their own files
- **Disk Management**: Monitor disk space and implement cleanup policies for old uploads
- **Backup**: Regularly backup the uploads folder separately from the code repository

### Best Practices

1. **Environment Variables**: Store sensitive data (database credentials, JWT secrets, API keys) in environment variables or `.env` file, NOT in version control
2. **Basic Authentication**: Use strong credentials and restrict access to trusted clients
3. **CORS**: Verify CORS configuration allows only trusted origins
4. **Input Validation**: Ensure all user inputs are validated and sanitized
5. **File Upload Validation**: Validate file types and sizes in the MediaService
6. **HTTPS**: Deploy with HTTPS in production
7. **Database**: Use encrypted passwords and principle of least privilege for database users

## 📚 API Endpoints

The application provides REST endpoints for:

- Authentication handled via HTTP Basic Auth (Spring Security)
- `/api/notes/**` - Note management
- `/api/folders/**` - Folder management
- `/api/media/**` - Media upload/download
- `/api/emails/**` - Email scheduling
- `/api/qrcode/**` - QR code generation
- `/api/pdf/**` - PDF processing
- `/api/search-history/**` - Search history

## 🧪 Testing

Run tests with:
```bash
cd demo
mvn test
```

## 📦 Building for Production

Create an optimized production build:
```bash
cd demo
mvn clean package -DskipTests
```

This generates a JAR file in the `target/` directory ready for deployment.

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify MySQL is running
   - Check database credentials in `application.properties`
   - Ensure the database exists

2. **Port Already in Use**
   - Change the port in `application.properties`: `server.port=8081`
   - Or kill the process using port 8080

3. **File Upload Issues**
   - Ensure `./uploads` directory exists and has proper permissions
   - Check disk space availability
   - Verify file size limits in Spring configuration

   ## 🔮 Future Improvements
- Migrate authentication from Basic Auth to JWT
- Add multi-user support and role-based authorization


## 📄 License

This project is private and intended for personal use.

## 👤 Author

**Syed Shariq**
- GitHub: [@Syed-Shariqq](https://github.com/Syed-Shariqq)

## 📞 Support

For issues or questions, please refer to the repository documentation or contact the author.

---

**Last Updated**: December 2025
