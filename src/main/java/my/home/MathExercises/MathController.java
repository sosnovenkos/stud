package my.home.MathExercises;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MathController {
    int operand1=1;
    int operand2=2;
    int answer;
    String operation;
    int okCount;
    int erCount;

    @GetMapping("/doex")
    public String getExercise(Model model){
            this.operand1 = (int) (Math.random() * 100);
            this.operand2 = (int) (Math.random() * 100);
            if (operand1<operand2)
            {
                int temp = operand2;
                operand2 = operand1;
                operand1 = temp;
            }
        if (this.operand1%2==0) operation="+";
                else operation="-";
        model.addAttribute("operand1", operand1);
        model.addAttribute("operand2", operand2);
        model.addAttribute("answer", answer);
        model.addAttribute("operation", operation);
        model.addAttribute("okcount", okCount);
        model.addAttribute("count", okCount+erCount);

        return "doex";
    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam("answer") int answer, Model model){
        String checked;

        if (operation.equals("+") ? operand1+operand2==answer : operand1-operand2==answer) {
            checked = ""+operand1+" "+operation+" "+operand2+" "+" = "+answer+" - правильно";
            okCount++;
        }
            else{
            checked = ""+operand1+" "+operation+" "+operand2+" "+" = "+answer+" - не правильно";
                erCount++;
        }

        model.addAttribute("checked", checked);
        model.addAttribute("okcount", okCount);
        model.addAttribute("count", okCount+erCount);
        return "check";
    }
}
