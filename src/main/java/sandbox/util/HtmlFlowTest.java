package sandbox.util;

import htmlflow.HtmlView;
import htmlflow.StaticHtml;
import sandbox.util.model.Funcionario;

public class HtmlFlowTest {

	public HtmlFlowTest() {
		
		Funcionario func = new Funcionario("Felipe", 19, "063");
		
		HtmlView<Funcionario> teste = StaticHtml.view().div().p().text(func.getNome()).__().__();
		String html = StaticHtml
                .view()
                    .html()
                        .head()
                            .title().text("HtmlFlow").__()
                        .__() //head
                        .body().of(__ -> StaticHtml.view().addPartial(teste))
                            .div().attrClass("container")
                                .h1().text("My first page with HtmlFlow").__()
                                .img().attrSrc("https://htmlflow.org/img/htmlflow-gravatar.png").__()
                                .p().text("Typesafe is awesome! :-)").__()
                            .__() //div
                        .__() //body
                    .__() //html
                .render();
		
		
		
		System.out.println(html);
	}
	
	public static void main(String[] args) {
		new HtmlFlowTest();
	}

}
