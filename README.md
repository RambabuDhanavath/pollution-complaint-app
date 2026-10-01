# Pollution Complaint App 🌍

> 🎓 **Academic project** — built during my undergrad mobile-developer internship at **CDAC Hyderabad**. A starter Android application demonstrating civic-tech concepts.

## Problem

Citizens who witness pollution — blaring loudspeakers at night, factory smoke, garbage burning, or adulterated food — usually have no easy way to report it. Complaints get lost in phone calls and paperwork, and authorities lack location-tagged, actionable reports.

## Solution

**Pollution Complaint App** lets citizens **file pollution complaints by location, from home**, using an Android app. Each complaint captures the pollution type, description, GPS location, and a photo — creating a clear, traceable record.

## ✨ Features

- 📝 **File a complaint** — choose the pollution type, describe the issue, attach a photo
- 📍 **Location-tagged reports** — GPS coordinates captured with every complaint
- 🗂️ **Complaint categories** — noise/sound pollution, air pollution, water pollution, food adulteration
- 📋 **My complaints** — track the status of submitted complaints (Submitted → In Review → Resolved)
- 💾 **Offline-first** — complaints saved locally in SQLite, synced when online
- 🔔 **Status updates** — see when authorities act on a report

## 🛠️ Tech Stack

- **Platform:** Android (Java)
- **Database:** SQLite (local storage)
- **Location:** Android Location Services / GPS
- **Camera:** Android Camera intents for photo evidence

## 🏗️ Architecture

```
┌─────────────────────┐
│   Android App       │
│                     │
│  MainActivity       │  complaint list (from SQLite)
│  FileComplaint      │ ──▶ captures type, description,
│    Activity         │     GPS location, photo
│  ComplaintDbHelper  │ ──▶ SQLite CRUD
│  Complaint (model)  │
└─────────────────────┘
```

## 📁 Project Structure

```
pollution-complaint-app/
├── app/
│   └── src/main/java/com/rambabu/pollutioncomplaint/
│       ├── MainActivity.java          # Home: list of filed complaints
│       ├── FileComplaintActivity.java # File a new complaint
│       ├── Complaint.java             # Complaint data model
│       └── ComplaintDbHelper.java     # SQLite database helper
└── README.md
```

## 🚀 Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/RambabuDhanavath/pollution-complaint-app.git
   ```

2. Open the `app/` project in **Android Studio**.
3. Build and run on a device or emulator (enable location services).
4. Tap **File Complaint**, pick a category, add details + photo, and submit.

## 📸 Screenshots

> Screenshots will be added here.

| Home | File Complaint | Complaint Details |
|---|---|---|
| _coming soon_ | _coming soon_ | _coming soon_ |

## 🗺️ Roadmap

- [ ] Backend API for syncing complaints to a central server
- [ ] Authority dashboard for reviewing and resolving complaints
- [ ] Push notifications on status changes
- [ ] Map view of reported pollution hotspots
- [ ] Multi-language support

## 📄 License

MIT
