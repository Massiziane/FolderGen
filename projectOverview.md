# FolderGen

## 1. Project Overview

**App Name:** FolderGen  
**Purpose:** Allow users to create, save, and reuse personal folder structure templates. Users can generate a folder structure anywhere on their system in one click.  
**Platform:** Desktop  
**Target Users:** Anyone who regularly sets up projects or recurring folder structures (developers, designers, students, etc.)

---

## 2. Core Features (MVP)

### Template Creation
- Users can create a folder structure template.  
- Assign a name to the template.  
- Include folders and optional files.  
- Ability to save templates for later use.

### Template Selection
- Users see a list of saved templates.  
- Select one template for folder generation.

### Folder Generation
- User selects a destination path.  
- Click **Generate** → Folder structure is created exactly as defined in the template.

### Persistence
- Templates are saved locally (JSON is simple and portable).

---

## 3. UI Structure

### Main Window (Dashboard)

**Template List:** Shows all saved templates with their names.  
**Buttons:**
- **New Template** → Opens editor.  
- **Edit Template** → Modify an existing template.  
- **Delete Template** → Remove unwanted templates.  
- **Generate** → Select destination and create folders.

### Template Editor Window

**Tree View:** Visual representation of folder structure.  
**Buttons:**
- **Add Folder**  
- **Add File (optional)**  
- **Delete Node**  
- **Rename Node**

### Generate Dialog

**Destination Path Input:** With file browser.  
**Buttons:**
- **Confirm**  
- **Cancel**

---

## 4. Data Structure

### Template JSON Format

```json
{
  "name": "MyProjectTemplate",
  "rootFolder": {
    "name": "ProjectName",
    "subfolders": [
      {
        "name": "src",
        "subfolders": [],
        "files": ["Main.java"]
      },
      {
        "name": "docs",
        "subfolders": [],
        "files": ["README.md"]
      }
    ],
    "files": []
  }
}
```
### Java Classes
```java
class Template {
    String name;
    FolderNode rootFolder;
}

class FolderNode {
    String name;
    List<FolderNode> subfolders;
    List<String> files; // optional
}
```
## 5. App Flow

1. **Startup:** Load all saved templates from local storage.  
2. **Template Management:** Create, edit, delete templates.  
3. **Generation:**  
   - User selects template.  
   - Selects destination.  
   - Clicks **Generate** → Folder structure is created.  
4. **Persistence:** Save any new or edited templates automatically.

---

## 6. Technical Stack

- **Language:** Java  
- **UI:** JavaFX  
- **Build:** Maven (for dependencies and packaging)  
- **Data Storage:** JSON (stored locally, e.g., `templates/` folder in app directory)
