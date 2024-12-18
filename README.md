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
   git clone https://github.com/mechkoor/Architecture-Kappa-pour-M-t-o-et-Longueur-des-Vagues.git
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
![image](https://github.com/user-attachments/assets/2c49330c-38c4-4099-a102-ac8918591ccc)


6. Upload the `final_flow.xml` template into Apache NiFi to set up the data pipeline, which sends data to Kafka.
![image](https://github.com/user-attachments/assets/f1db030a-f7e1-4b41-8d4d-e1988da0e462)


7. Once the pipeline is set up, the data will be processed and stored in Kafka.

8. Build the project using Maven by running the following command:
   ```bash
   mvn clean package
   ```

9. Access the Flink web interface. In the Flink interface, click on **Submit New Job** and select the JAR file generated during the Maven build process. It will be located in the `target` folder of your project.
![image](https://github.com/user-attachments/assets/10b9af84-47a0-49ee-9804-52f51546049d)


10. In the **Entry Class**, enter the path to the main class (e.g., `com.example.flink.DataStreamJob`).
![image](https://github.com/user-attachments/assets/1cf5e595-d451-42fc-be2e-a046d63eae99)


11. Submit the job to start processing the data.
![image](https://github.com/user-attachments/assets/e354edae-2152-42dc-a146-08594f05cac8)



12. Go to Kibana, create an index pattern for the index created by Flink (e.g., `weather`).
![image](https://github.com/user-attachments/assets/8d470cab-59fa-48e0-b0a1-1bef0eed2f79)


13. Click on **Discover** to see data consumed in real-time.
![image](https://github.com/user-attachments/assets/8007cb37-47be-493d-b906-aff8659a75c6)


14. Go to **Stack Management** -> **Saved Objects** and import the dashboard.

---

## Dashboard

To display real-time visualizations in Kibana by importing a dashboard file, here are the steps:

1. **Go to "Stack Management"**  
   In the left-hand menu, click on **Management > Stack Management**.

2. **Click on "Saved Objects"**  
   In the **Stack Management** section, locate **"Saved Objects"** and click on it.  
   This allows you to manage saved objects such as dashboards, visualizations, and index patterns.

3. **Import the JSON file**  
   Click the **"Import"** button in the top-right corner.  
   Select the **dashboard JSON file** located in your project.

<img width="960" alt="5" src="https://github.com/user-attachments/assets/8acacfa4-99c3-4f21-8cab-a3bef334413b" />
A dashboard containing real-time charts and graphs, including:

- **Temperature by city**: A graph showing the evolution of temperatures for each city.  
- **Relationship between wind speed and wave length**: A graph correlating these two variables.  
- **Wave length by day for each city**: A graph showing daily variations in wave length for each city.  
- **Wind direction (wind degree) for each city**: A visualization indicating the wind direction (in degrees) in different cities.


![image](https://github.com/user-attachments/assets/21fee6fd-1dde-467b-9c0c-2032d5003ed6)

