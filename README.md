# Kappa Architecture for Weather and Wave Length Data

This project implements a Kappa architecture using Apache Flink, Apache NiFi, Elasticsearch, and Kibana for real-time data processing and visualization. The platform processes two APIs: one for weather data and another for wave length data. The solution leverages a robust data pipeline to efficiently manage, analyze, and visualize data, providing valuable insights for real-time decision-making.

---

## Table of Contents

1. [Features](#features)
2. [Project Architecture](#project-architecture)
3. [Prerequisites](#prerequisites)
4. [Installation](#installation)
5. [Dashboard](#dashboard)

---

## Features

- **Real-Time Data Retrieval:** Use Apache NiFi to fetch weather and wave length data from external APIs.
- **Data Pipeline:** Utilize Apache NiFi for data collection and integration from the APIs, and Kafka for buffering and stream processing between NiFi and Flink.
- **Data Processing:** Leverage Apache Flink for real-time processing and analysis of incoming data.
- **Search and Storage:** Efficiently store and retrieve processed data using Elasticsearch.
- **Data Visualization:** Visualize real-time data and analytics with Kibana for weather and wave length insights.

---

## Project Architecture
![WhatsApp Image 2024-12-17 at 7 28 35 PM](https://github.com/user-attachments/assets/c280b7fb-b1c4-45f1-b590-48114c761187)


### Services

- **Weather API:** Provides real-time weather data (e.g., temperature, wind speed).
- **Wave Length API:** Fetches real-time wave length data for marine conditions.
- **Apache NiFi:** Handles data collection and integration from external APIs and pushes the data into Kafka.
- **Apache Kafka:** Acts as a buffer and message queue, storing data temporarily between NiFi and Flink for stream processing.
- **Apache Flink:** Processes and analyzes the incoming data streams from Kafka in real-time.
- **Elasticsearch:** Stores, indexes, and retrieves processed data for efficient searching.
- **Kibana:** Visualizes data in real-time through an interactive dashboard.

### Docker Architecture

- The architecture includes services such as NiFi, Zookeeper, Flink (Master and Worker nodes), Kafka brokers, Elasticsearch nodes, and Kibana.
- Custom networks and persistent volumes are used to ensure secure and isolated communication between services.

![image](https://github.com/user-attachments/assets/4b33b886-6907-4ef4-93c2-8906fec227a5)


---

## Prerequisites

Ensure the following tools are installed:

- [Docker](https://www.docker.com/)
- [Git](https://git-scm.com/)
- [Maven](https://maven.apache.org/)

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/YourUsername/weather_wave_kappa.git
   ```
2. Navigate to the project directory:
   ```bash
   cd kappa_project
   ```
3. Open the project in your preferred IDE and review the architecture.

4. Start all services:
   ```bash
   docker-compose up -d
   ```
5. Verify that all containers are running.

   - If any container fails, open Docker Desktop and manually start the relevant containers.

6. Upload the `final_flow.xml` template into Apache NiFi to set up the data pipeline, which sends data to Kafka.

7. Once the pipeline is set up, the data will be processed and stored in Kafka.

8. Build the project using Maven by running the following command:
   ```bash
   mvn clean package
   ```

9. Access the Flink web interface. In the Flink interface, click on **Submit New Job** and select the JAR file generated during the Maven build process. It will be located in the `target` folder of your project.

10. In the **Entry Class**, enter the path to the main class (e.g., `com.example.flink.DataStreamJob`).

11. Submit the job to start processing the data.

12. Go to Kibana, create an index pattern for the index created by Flink (e.g., `weather`).

13. Click on **Discover** to see data consumed in real-time.

14. Go to **Stack Management** -> **Saved Objects** and import the dashboard.

---

## Dashboard

The dashboard provides:

- Real-time weather data visualization.
- Wave length data visualization.
- Interactive tools for querying and analyzing data in Kibana.

![Dashboard](misc/Dashboard.png)
