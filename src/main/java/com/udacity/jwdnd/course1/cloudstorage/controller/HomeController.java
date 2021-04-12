package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@SuppressWarnings("unused")
@Controller
public class HomeController {

    @Autowired
    private FilesService filesService;
    @Autowired
    private NotesService notesService;
    @Autowired
    CredentialsService credentialsService;
    @Autowired
    private UserService userService;

    private boolean showFiles = true;
    private boolean showNotes;
    private boolean showCredentials;

    @GetMapping("/home")
    public String getData(Principal principal,
                          Model model
    ) {
        User user = userService.getUser(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }

        List<File> files = filesService.getFilesByUserId(user.getUserId());
        List<Note> notes = notesService.getNotesByUserId(user.getUserId());
        List<Credential> credentials = credentialsService.getCredentialsByUserId(user.getUserId());

        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);

        model.addAttribute("modalNote", new Note(0, "", ""));
        model.addAttribute("modalCredential", new Credential(0, "", "", "", ""));

        model.addAttribute("showFiles", showFiles);
        model.addAttribute("showNotes", showNotes);
        model.addAttribute("showCredentials", showCredentials);

        return "home";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file,
                                   Principal principal,
                                   RedirectAttributes redirectAttributes) {

        User user = userService.getUser(principal.getName());
        if (user != null) {
            String saveResultMsg = filesService.store(file, user.getUserId());
            if (saveResultMsg.isEmpty()) {
                saveResultMsg = "You successfully uploaded "
                        + file.getOriginalFilename() + "!";
            }
            redirectAttributes.addFlashAttribute("message",
                    saveResultMsg + "!");
        }
        toggleNavTabs(true, false, false);
        return "redirect:/home";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        toggleNavTabs(true, false, false);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/file/{fileid}")
    public ResponseEntity<byte[]> getFile(@PathVariable int fileid) {
        File file = filesService.loadFile(fileid);

        toggleNavTabs(true, false, false);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file.getFiledata());
    }

    @GetMapping("/deletefile/{fileid}")
    public String deleteFile(@PathVariable int fileid,
                             Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user != null) {
            filesService.deleteFile(fileid, user.getUserId());
        }
        toggleNavTabs(true, false, false);
        return "redirect:/home";
    }

    @GetMapping("/deletenote/{noteid}")
    public String deleteNote(@PathVariable int noteid,
                             Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user != null) {
            notesService.deleteNote(noteid, user.getUserId());
        }
        toggleNavTabs(false, true, false);
        return "redirect:/home";
    }

    @PostMapping("/addeditnote")
    public String addEditNote(@ModelAttribute("modalNote") Note note,
                              Model model,
                              Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user != null) {
            if (note.getNoteId() > 0)
                notesService.editNote(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
            else
                notesService.store(note.getNoteTitle(), note.getNoteDescription(), user.getUserId());
        }

        toggleNavTabs(false, true, false);
        return "redirect:/home";
    }

    @GetMapping("/deletecredential/{credentialid}")
    public String deleteCredential(@PathVariable int credentialid,
                                   Principal principal) {
        User user = userService.getUser(principal.getName());

        if (user != null) {
            credentialsService.deleteCredential(credentialid, user.getUserId());
        }
        toggleNavTabs(false, false, true);
        return "redirect:/home";
    }

    @PostMapping("/addeditcredential")
    public String addEditCredential(@ModelAttribute("modalCredential") Credential credential,
                                    Model model,
                                    Principal principal) {
        User user = userService.getUser(principal.getName());


        if (user != null) {
            String key = "";
            if (credential.getCredentialid() > 0)
                key = credentialsService.editCredential(
                        credential.getCredentialid(),
                        credential.getUrl(),
                        credential.getUsername(),
                        credential.getPassword());
            else
                key = credentialsService.store(
                        credential.getUrl(),
                        credential.getUsername(),
                        credential.getPassword(),
                        user.getUserId()
                );
            credential.setKey(key);
        }
        toggleNavTabs(false, false, true);

        return "redirect:/home";
    }

    private void toggleNavTabs(boolean showFiles,
                               boolean showNotes,
                               boolean showCredentials) {
        this.showFiles = showFiles;
        this.showNotes = showNotes;
        this.showCredentials = showCredentials;
    }
}
