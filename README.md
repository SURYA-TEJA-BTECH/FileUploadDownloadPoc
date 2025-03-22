# FileUploadDownloadPoc

FileUploadDownloadPoc is a **Spring Boot** application that demonstrates storing and retrieving files using an **H2 Database**. This is a **Proof of Concept (PoC)** and is not intended for production use.

## Features

- **File Upload:** Upload files (images, PDFs, Word documents) directly to the H2 database.
- **File Retrieval:** Retrieve files using a file ID.
- **Spring Boot & H2 Integration:** Uses an in-memory H2 database for temporary storage.

## Why Storing Files in a Database is NOT Recommended

Storing files (binary data) directly in a relational database is **not a best practice**, especially for high-traffic applications. Here’s why:

- **Performance Issues:** Databases are optimized for structured data, not large binary objects.
- **Increased DB Load:** Large files increase database size, slowing down queries and backups.
- **Scaling Issues:** Harder to distribute files across multiple servers efficiently.

### When is it Acceptable?
- Only recommended for **low-traffic applications** or **small-scale proof of concepts**.


## Best Practices for File Storage

- **Store files in a server’s file system** and save the file path in the database.
- **Use cloud storage buckets** (Recommended for scalability and reliability), e.g., AWS S3, Google Cloud Storage, or Azure Blob Storage.

## API Endpoints

### File Operations

| Method | Endpoint          | Description             |
|--------|------------------|-------------------------|
| POST   | `/upload`        | Upload a file           |
| GET    | `/download/{id}` | Retrieve a file by ID   |

## Technologies Used

- **Spring Boot**
- **H2 Database** (In-Memory for PoC)
- **Spring Data JPA**




![image](https://github.com/user-attachments/assets/7025137a-d722-4fbc-a696-44ac06b2c8d2)


![image](https://github.com/user-attachments/assets/2f56f0f5-be7c-4d46-b06f-487ad722fffc)

![image](https://github.com/user-attachments/assets/5ab3135e-9a46-491c-9bfd-5816d52f136d)


