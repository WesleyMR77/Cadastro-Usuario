package com.ifms.wmr.controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.ifms.wmr.model.Usuario;
import com.ifms.wmr.model.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuRepo;
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		Usuario u;
		u = new Usuario();
		model.addAttribute("u", u);
		return "telalogin";
	}
	
	@PostMapping("/login")
	public String autenticarUsuario(Model model, Usuario u) {
		List<Usuario> usuario = usuRepo.findAll();
		//JOptionPane.showMessageDialog(null, usuario.get(0).getEmail());
		System.out.println(usuario.get(1).getEmail());
		System.out.println(u.getEmail());
		System.out.println(usuario.size());
		for(int i = 0; i < usuario.size(); i++) {
			if(usuario.get(i).getEmail().equals(u.getEmail()) && usuario.get(i).getSenha().equals(u.getSenha()) ) {
				return "index";
			}
		}
		return "redirect:/login";		
	}
	
	@GetMapping("/cadastro")
	public String showCadastro(Model model) {
		Usuario usuario;
		usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "telacadastro";
	}
	
	@PostMapping("/cadastro")
	public String cadUser(Usuario usuario) {
		usuRepo.save(usuario);
		return "redirect:/login";
	}

}
