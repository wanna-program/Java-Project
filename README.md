# Travel Company E-Shop

Welcome to the **Travel Company E-Shop** project! This application simulates an online ticket purchasing system for a travel company. It allows customers to purchase tickets for various itineraries, generates reports, and demonstrates exception handling in Java.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Exception Handling](#exception-handling)
- [Reports](#reports)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

---

## Project Overview

The Travel Company E-Shop is a Java-based console application that simulates the operations of a travel company's e-commerce platform. It handles customer and itinerary management, ticket purchasing with pricing logic, and generates various reports to analyze sales and customer behavior.

---

## Features

- **Customer Management**: Add and manage customers with different categories (Individual, Business).
- **Itinerary Management**: Create and manage itineraries with departure and destination airport codes, departure times, airlines, and prices.
- **Ticket Purchasing**: Customers can purchase tickets for itineraries using different payment methods (Cash, Credit Card).
- **Pricing Logic**:
  - Discounts and surcharges based on customer category.
  - Payment method discounts.
- **Exception Handling**:
  - Custom exceptions for specific error scenarios.
  - Handling invalid email addresses, non-existent customers or itineraries, and invalid airport codes.
- **Report Generation**:
  - Customer Report.
  - Itinerary Report.
  - Ordered Tickets Report.
  - Total Tickets and Cost per Customer.
  - Total Itineraries per Destination and Departure.
  - Top Customers by Tickets and Cost.
  - Customers with No Tickets.

---

## Technologies Used

- **Java SE 8 or higher**
- **JDK 23
- **Java Collections Framework**
- **Java Stream API**
- **Date and Time API (Java 8+)**

---

## Project Structure

src/ ├── com.travelcompany.eshop.exceptions/ │   ├── CustomerEmailException.java │   ├── CustomerNotFoundException.java │   ├── InvalidAirportCodeException.java │   └── ItineraryNotFoundException.java ├── com.travelcompany.eshop.main/ │   ├── DataLoader.java │   └── Main.java ├── com.travelcompany.eshop.models/ │   ├── Customer.java │   ├── CustomerCategory.java │   ├── Itinerary.java │   ├── PaymentMethod.java │   ├── Person.java │   └── Ticket.java └── com.travelcompany.eshop.services/ ├── CustomerService.java ├── CustomerServiceImpl.java ├── ItineraryService.java ├── ItineraryServiceImpl.java ├── ReportService.java ├── ReportServiceImpl.java ├── TicketService.java └── TicketServiceImpl.java

