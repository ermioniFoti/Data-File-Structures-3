# Text File Search & Indexing with B+-Trees

A high-performance text indexing and retrieval system designed to manage and search through large text files efficiently. This project was developed as the **3rd Assignment** for the **Data Structures and Algorithms** course at the **Technical University of Crete** (Spring 2023).

## 📌 Project Overview
The objective of this application is to construct an in-memory search engine capable of indexing multiple text files and performing rapid keyword lookups. Instead of searching raw text files linearly (which is slow for large datasets), the system builds a robust **Index Structure** consisting of a **Dictionary** and an **Index**.

### Core Architecture
The system utilizes a dual-structure approach to map words to their exact locations across multiple files:

1.  **Dictionary (B+-Tree):** Stores unique words (keys) found in the text files. A **B+-Tree** is used to ensure balanced and efficient access.
2.  **Index (Linked Lists):** For each word in the B+-Tree, a linked list stores the occurrence details. Each node in the list contains:
    * The **Filename** where the word appears.
    * The **Position** (offset) of the word within that file.



## 🚀 Features

### 1. File Ingestion & Indexing
The program accepts text files (containing single-line ASCII text) and parses them word by word.
* **New Words:** If a word is not in the B+-Tree, it is inserted, and a new linked list is created.
* **Existing Words:** If a word already exists, the new file/position details are appended to its corresponding linked list.

### 2. Search & Retrieval
Users can query the system for any word. The program traverses the B+-Tree to find the key and then iterates through the linked list to print every occurrence (file and position).

### 3. Performance Analysis
The system instruments the search process to collect performance metrics. For every search operation, it tracks:
* **Node Accesses:** The number of B+-Tree nodes visited.
* **Key Comparisons:** The total number of key comparisons performed.

## 📊 Benchmarking
The project includes a benchmarking suite that performs **100 searches** (50 words randomly selected from `1.txt` and 50 from `2.txt`) to evaluate the efficiency of the B+-Tree under different orders (**M**).

**Metrics Collected:**
1.  Average **Node Accesses** per search.
2.  Average **Key Comparisons** per search.

**Experimental Scenarios:**
* **Scenario A:** B+-Tree Order $M = 10$.
* **Scenario B:** B+-Tree Order $M = 20$.

## 🛠️ Implementation Details
* **Data Structures:**
    * **B+-Tree:** Used for the Dictionary (Order $M$ is configurable).
    * **Singly Linked List:** Used for the Index (occurrence records).
* **Code Standards:**
    * Parametric design allowing easy modification of tree order $M$ and other constants.
    * Clean, structured code with explanatory comments.

---
*Developed for the School of Electrical and Computer Engineering, Technical University of Crete.*
