package com.github.devcode.studio.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ConnectionConfigDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTree tree;
    private JTabbedPane tabbedPane;

    public ConnectionConfigDialog(JFrame owner) {
        super(owner, "연결정보 설정", true);
        setSize(900, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        add(createTopDescription(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomButtons(), BorderLayout.SOUTH);
    }

    private JComponent createTopDescription() {
        JLabel desc = new JLabel(
            "<html><b>연결정보 관리</b><br>" +
            "좌측에서 설정을 선택하고, 우측 탭에서 상세 정보를 편집하십시오.</html>"
        );
        desc.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        return desc;
    }

    private JComponent createCenterPanel() {
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(250);

        tree = createConnectionTree();
        tabbedPane = createTabbedPanel();

        split.setLeftComponent(new JScrollPane(tree));
        split.setRightComponent(tabbedPane);

        return split;
    }

    private JComponent createBottomButtons() {
        JButton okBtn = new JButton("확인");
        JButton closeBtn = new JButton("닫기");

        closeBtn.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(okBtn);
        panel.add(closeBtn);

        return panel;
    }
    
 
    private JTree createConnectionTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Connections");

        DefaultMutableTreeNode dbGroup = new DefaultMutableTreeNode("Database");
        dbGroup.add(new DefaultMutableTreeNode("DEV-DB"));
        dbGroup.add(new DefaultMutableTreeNode("PROD-DB"));

        DefaultMutableTreeNode apiGroup = new DefaultMutableTreeNode("API");
        apiGroup.add(new DefaultMutableTreeNode("Auth Server"));
        apiGroup.add(new DefaultMutableTreeNode("Gateway"));

        root.add(dbGroup);
        root.add(apiGroup);

        JTree tree = new JTree(root);

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null || node.isRoot()) return;

            loadConnectionToTabs(node.toString());
        });

        return tree;
    }
    
    
    private JTabbedPane createTabbedPanel() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("기본정보", createBasicPanel());
        tabs.addTab("보안", createSecurityPanel());
        tabs.addTab("고급설정", createAdvancedPanel());

        return tabs;
    }
    
    private JPanel createBasicPanel() {
        JPanel p = new JPanel(new GridLayout(4,2,5,5));
        p.add(new JLabel("이름")); p.add(new JTextField());
        p.add(new JLabel("Host")); p.add(new JTextField());
        p.add(new JLabel("Port")); p.add(new JTextField());
        p.add(new JLabel("설명")); p.add(new JTextField());
        return p;
    }

    private JPanel createSecurityPanel() {
        JPanel p = new JPanel(new GridLayout(2,2,5,5));
        p.add(new JLabel("User")); p.add(new JTextField());
        p.add(new JLabel("Password")); p.add(new JPasswordField());
        return p;
    }

    private JPanel createAdvancedPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JCheckBox("SSL 사용"), BorderLayout.NORTH);
        p.add(new JCheckBox("자동 재연결"), BorderLayout.CENTER);
        return p;
    }
    
    private void loadConnectionToTabs(String name) {
        // 여기서 JSON / DB / 파일에서 불러와
        // 각 탭의 필드에 값 세팅
        System.out.println("선택된 연결: " + name);
    }
    
    
}