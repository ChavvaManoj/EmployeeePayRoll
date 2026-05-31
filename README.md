# Employee Payroll Management System

## Overview

Employee Payroll Management System is a backend application built using Spring Boot and PostgreSQL that automates employee salary processing.

The project focuses on learning and implementing modern backend concepts such as:

* Spring Boot REST APIs
* JPA/Hibernate
* Scheduler-based processing
* Batch Processing
* Parallel Processing using ExecutorService
* Event-Driven Architecture (Planned)
* Kafka Integration (In Progress)

---

# Current Features

## Master Data Management

### Department Management

* Create Department
* Update Department
* Get Department by ID
* Get All Departments

### Designation Management

* Create Designation
* Update Designation
* Get Designation by ID
* Get All Designations

### Pay Level Management

* Create Pay Level
* Update Pay Level
* Get Pay Level by ID
* Get All Pay Levels

### Salary Component Management

* Create Salary Components
* Dynamic DA Configuration
* Dynamic HRA Configuration
* Active/Inactive Salary Component Support

---

# Employee Management

## Employee Details

* Employee Code
* Employee Name
* Email
* Department Mapping
* Designation Mapping
* Pay Level Mapping

## Posting Details

* Office Name
* Location
* From Date
* To Date

## Payment Details

* Bank Name
* Account Number
* IFSC Code
* Effective From
* Effective To
* Active Flag

---

# Payroll Processing

## Salary Run

Payroll execution is initiated through a Salary Run.

Each Salary Run maintains:

* Month
* Year
* Status
* Total Employees
* Processed Employees
* Failed Employees
* Start Time
* Completion Time
* Processing Duration

---

## Salary Calculation

Current Salary Formula:

Gross Salary = Basic Pay + HRA + DA

Where:

* Basic Pay comes from Pay Level
* HRA comes from active Salary Component
* DA comes from active Salary Component

Generated data is stored in:

* SalaryRun
* SalaryDetail

---

# Batch Processing

Employees are grouped into configurable batches.

Current Configuration:

* Batch Size = 5 Employees

Example:

Batch 1

* Employee 1
* Employee 2
* Employee 3
* Employee 4
* Employee 5

Batch 2

* Employee 6
* Employee 7
* Employee 8
* Employee 9
* Employee 10

---

# Parallel Processing

Current implementation uses:

ExecutorService

Benefits:

* Faster payroll execution
* Independent batch processing
* Improved scalability
* Reduced overall processing time

Flow:

Scheduler
↓
Salary Run
↓
Employee Batches
↓
ExecutorService
↓
Parallel Processing
↓
Salary Detail Generation

---

# Scheduler

Implemented using:

@Scheduled

Current Scheduler Responsibilities:

* Check for pending payroll runs
* Trigger payroll processing automatically
* Monitor payroll execution status

---

# Database

Technology:

* PostgreSQL

Core Tables:

* employee
* department
* designation
* pay_level
* posting_detail
* payment_detail
* salary_component
* salary_run
* salary_detail

---

# Technology Stack

Backend:

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate

Database:

* PostgreSQL

Build Tool:

* Gradle

Containerization:

* Docker

Messaging:

* Apache Kafka (Work In Progress)

---

# Kafka Integration (In Progress)

Kafka infrastructure has been set up.

Completed:

* Kafka Docker Setup
* Kafka Producer Configuration
* Kafka Consumer Configuration
* Kafka Topic Creation
* SalaryBatchMessage DTO
* Kafka Topic Constants
* Kafka Message Serialization

Current Topic:

salary-processing

---

# Planned Kafka Architecture

Current Flow:

Scheduler
↓
Batch Creation
↓
ExecutorService
↓
Payroll Processing

Future Flow:

Scheduler
↓
Batch Creation
↓
Kafka Producer
↓
salary-processing Topic
↓
Kafka Consumer
↓
Payroll Processing
↓
Salary Detail Generation

Benefits:

* Event-driven architecture
* Horizontal scalability
* Distributed processing
* Better fault tolerance
* Independent processing services

---

# Future Enhancements

## Redis Integration

* Payroll Cache
* Employee Cache
* Salary Component Cache
* Performance Optimization

## API Documentation

* Swagger/OpenAPI

## Exception Handling

* Global Exception Handler
* Standard API Responses

## Testing

* Unit Tests
* Integration Tests

## Dockerization

* Spring Boot Container
* PostgreSQL Container
* Kafka Container
* Docker Compose Setup

## Monitoring

* Spring Boot Actuator
* Application Metrics
* Health Checks

---

# Learning Objectives Achieved

✅ Spring Boot REST APIs

✅ JPA/Hibernate Relationships

✅ PostgreSQL Integration

✅ Scheduler-Based Processing

✅ Batch Processing

✅ Parallel Processing

✅ ExecutorService

✅ Payroll Calculation Engine

✅ Docker Basics

✅ Kafka Fundamentals

---

# Project Status

Current Stage:

Scheduler + Parallel Payroll Processing Complete

Next Milestone:

Replace ExecutorService-based processing with Kafka-driven event processing and integrate Redis caching.
