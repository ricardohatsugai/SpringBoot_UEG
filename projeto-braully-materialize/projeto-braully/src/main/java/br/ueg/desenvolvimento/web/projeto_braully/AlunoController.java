package br.ueg.desenvolvimento.web.projeto_braully;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController {

    static List alunos = new ArrayList<>();

    static {
        alunos.add(Map.of("nome", "João", "email", "joao@localhost"));
        alunos.add(Map.of("nome", "Maria", "email", "maria@localhost"));
    }

    @GetMapping("/alunos")
    public String getHome(Model model) {
        model.addAttribute("alunos", alunos);
        model.addAttribute("mensagem", "Todos os alunos cadastrados");
        return "alunos.html";
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/alunos/create")
    public String getCreate() {
        return "aluno-create.html";
    }

    @PostMapping("/alunos/create")
    public String postCreate(@RequestParam String nome, @RequestParam String email) {
        alunos.add(Map.of("nome", nome, "email", email));
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/update/{id}")
    public String getUpdate(@PathVariable int id, Model model) {
        model.addAttribute("aluno", alunos.get(id));
        model.addAttribute("id", id);
        return "aluno-update.html";
    }

    @PostMapping("/alunos/update")
    public String postUpdate(@RequestParam int id, @RequestParam String nome, @RequestParam String email) {
        alunos.set(id, Map.of("nome", nome, "email", email));
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/delete/{id}")
    public String getDelete(@PathVariable int id, Model model) {
        model.addAttribute("aluno", alunos.get(id));
        model.addAttribute("id", id);
        return "aluno-delete.html";
    }

    @PostMapping("/alunos/delete")
    public String postDelete(@RequestParam int id) {
        alunos.remove(id);
        return "redirect:/alunos";
    }
}
