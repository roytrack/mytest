package com.roytrack.svn;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc2.SvnLog;

public class TestSvn {
	
	public static void main(String[] args) throws Exception {
		TestSvn ts=new TestSvn("svn://192.168.100.240/DEV","ruanchangming","123456");
		ts.login();
		Collection bb=ts.listEntries("/SOURCE/Temp/bxfundacc/bxfundacc_temp/src/com/yss/entity");
		Iterator it=bb.iterator();
		while(it.hasNext()){
			SVN  cc=(SVN)it.next();
			System.out.println(cc.toStrings());
		}
			
	}
	private String svnRoot;

    private String userName;

    private String password;

    private SVNRepository repository;

   

    /***

     * ���췽��

     * @param svnRoot

     *             svn��Ŀ¼

     */

    public TestSvn(String svnRoot) {

       this.svnRoot=svnRoot;

    }

    /***

     * ���췽��

     * @param svnRoot

     *             svn��Ŀ¼

     * @param userName

     *             ��¼�û���

     * @param password

     *             ��¼����

     */

    public TestSvn(String svnRoot, String userName, String password) {

       this.svnRoot=svnRoot;

       this.userName=userName;

       this.password=password;

    }

/***

     * ͨ����ͬ��Э���ʼ���汾��

     */

    private static void setupLibrary() {

       // ����ʹ��http://��https��//

       DAVRepositoryFactory.setup();

       //����ʹ��svn��/ /��svn+xxx��/ /

       SVNRepositoryFactoryImpl.setup();

       //����ʹ��file://

       FSRepositoryFactory.setup();

}

//ÿ�����ӿⶼ���е�½��֤

    /***

     * ��¼��֤

     * @return

     */

    public boolean login(){

       setupLibrary();

       try{

           //����������

          repository=SVNRepositoryFactory.create(SVNURL.parseURIEncoded(this.svnRoot));

           //�����֤

           ISVNAuthenticationManager authManager = SVNWCUtil

           .createDefaultAuthenticationManager(this.userName,

                  this.password);

           //���������֤������

           repository.setAuthenticationManager(authManager);

           return true;

       } catch(SVNException svne){

           svne.printStackTrace();

           return false;

       }

    }

//����ķ���ʵ�ֲ�ѯ����·���µ���Ŀ�б���

/***

     *

     * @param path

     * @return ��ѯ����·���µ���Ŀ�б�

     * @throws SVNException

     */

    @SuppressWarnings("rawtypes")

    public List<SVN> listEntries(String path)

           throws SVNException {

       Collection entries = repository.getDir(path, -1, null,

              (Collection) null);

       Iterator iterator = entries.iterator();

       List<SVN> svns = new ArrayList<SVN>();

       while (iterator.hasNext()) {

           SVNDirEntry entry = (SVNDirEntry) iterator.next();
           
           SVN svn = new SVN();

           svn.setCommitMessage(entry.getCommitMessage());
           System.out.println();
           svn.setDate(entry.getDate());

           svn.setKind(entry.getKind().toString());

           svn.setName(entry.getName());

           svn.setRepositoryRoot(entry.getRepositoryRoot().toString());

           svn.setRevision(entry.getRevision());

           svn.setSize(entry.getSize()/1024);

           svn.setUrl(path.equals("") ? "/"+entry.getName() : path +"/"+entry.getName());

           svn.setAuthor(entry.getAuthor());

           svn.setState(svn.getKind() == "file"?null:"closed");

           svns.add(svn);

       }
       return svns;
    }
    
    public String thelog(){
    	//SvnLog aa=
    	return "";
    }
    
 
}