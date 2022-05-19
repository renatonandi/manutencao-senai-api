package br.com.senai.manutencaosenaiapi.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.senai.manutencaosenaiapi.entity.TipoPeca;
import br.com.senai.manutencaosenaiapi.service.TipoPecaService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class TelaCadastroTipoPeca extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtDescricao;
	
	@Autowired
	private TipoPecaService service;
	
	@Autowired
	@Lazy
	private TelaConsultaTipoPeca telaDeConsultaTipoPeca;

	
	

	
	public TelaCadastroTipoPeca() {
		setTitle("Cadastro de Tipos de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblDescricao = new JLabel("Descrição");
		
		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(edtId.getText() != null && edtId.getText().length() > 0) {
						TipoPeca tipoPecaSalva = new TipoPeca();
						tipoPecaSalva.setDescricao(edtDescricao.getText());
						tipoPecaSalva.setId(Integer.parseInt(edtId.getText()));
						service.alterar(tipoPecaSalva);
						JOptionPane.showMessageDialog(contentPane, "Tipo da peça alterada com sucesso");
						
						
					}else {
						TipoPeca novoTipoPeca = new TipoPeca();
						novoTipoPeca.setDescricao(edtDescricao.getText());
						TipoPeca tipoPecaSalva = service.inserir(novoTipoPeca);
						edtId.setText(tipoPecaSalva.getId().toString());
						JOptionPane.showMessageDialog(contentPane, "Peça inserida com sucesso");
						
					}
					
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
					
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescricao)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(471, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblDescricao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(btnSalvar))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void colocarEmEdicao(TipoPeca tipoPecaSalva) {
		edtId.setText(tipoPecaSalva.getId().toString());
		edtDescricao.setText(tipoPecaSalva.getDescricao());
	}
}
