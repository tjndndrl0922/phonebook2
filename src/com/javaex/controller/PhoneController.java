package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

//http://localhost:8088/phonebook2/pbc
@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 컨트롤러 테스트
		System.out.println("controller");

		// 파라미터 action값을 읽어서
		String action = request.getParameter("action");
		System.out.println(action);

		// action =list

		if ("wform".equals(action)) {
			System.out.println("등록 폼 처리");

			/*
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher("./WEB-INF/writeForm.jsp"); rd.forward(request,
			 * response);
			 */
			WebUtil.forword(request, response, "./WEB-INF/writeForm.jsp");

		} else if ("insert".equals(action)) {
			System.out.println("전화번호 저장");

			// 파라미터 3개 값
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			// personVo묶고
			PersonVo personVo = new PersonVo(name, hp, company);

			// new dao --> 저장
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
			/*
			 * response.sendRedirect("/phonebook2/pbc?action=list");
			 */
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");

		} else if ("uform".equals(action)) {
			System.out.println("수정 폼 처리");

			int update = Integer.parseInt(request.getParameter("update"));

			PhoneDao phoneDao = new PhoneDao();

			PersonVo personVo = phoneDao.getPerson(update);

			request.setAttribute("uList", personVo);
			/*
			 * // jsp에 포워드 시킨다. RequestDispatcher rd =
			 * request.getRequestDispatcher("./WEB-INF/updateForm.jsp"); rd.forward(request,
			 * response);
			 */
			String path = "./WEB-INF/updateForm.jsp";
			WebUtil.forword(request, response, path);

		} else if ("update".equals(action)) {
			System.out.println("전화번호 수정");

			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int update = Integer.parseInt(request.getParameter("update"));

			PersonVo personVo = new PersonVo(update, name, hp, company);

			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			/*
			 * response.sendRedirect("/phonebook2/pbc?action=list");
			 */
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");

		} else if ("delete".equals(action)) {
			System.out.println("전화번호 삭제");

			int delete = Integer.parseInt(request.getParameter("delete"));

			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(delete);
			/*
			response.sendRedirect("/phonebook2/pbc?action=list");
			*/
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
		} else { // 기본값을 리스트로

			System.out.println("리스트 처리");
			// 리스트 출력 처리
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();

			// html --> 엄청 복잡하다 --> jsp가 편함
			// 데이터전달
			request.setAttribute("pList", personList);

			// jsp에 포워드 시킨다.
			/*
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); // jsp파일 위치. 접근을 못하게 WEB-INF에 둠
			rd.forward(request, response);
			*/
			WebUtil.forword(request, response, "./WEB-INF/list.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

	}

}
