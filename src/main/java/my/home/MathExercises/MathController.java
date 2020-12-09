package my.home.MathExercises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    UserExer userExer;
    int answer;

    @GetMapping("/doex")
    public String getExercise(UserExer userExer, Model model){
        //this.userExer = userExer;
        this.userExer.operand1 = (int) (Math.random() * 100);
        this.userExer.operand2 = (int) (Math.random() * 100);
            if (this.userExer.operand1<this.userExer.operand2)
            {
                int temp = this.userExer.operand2;
                this.userExer.operand2 = this.userExer.operand1;
                this.userExer.operand1 = temp;
            }
        if (this.userExer.operand1%2==0) this.userExer.operation="+";
                else this.userExer.operation="-";
          model.addAttribute("userExer",this.userExer);
          model.addAttribute("answer", answer);
        return "doex";
    }

    @GetMapping("/")
    public String getStarted(Model model){

        this.userExer = new UserExer();
        return "redirect:/doex";
    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam("answer") int answer, Model model){
        this.userExer.answer=answer;
        if (this.userExer.operation.equals("+") ? this.userExer.operand1+this.userExer.operand2==this.userExer.answer : this.userExer.operand1-this.userExer.operand2==this.userExer.answer) {
            this.userExer.checked = ""+this.userExer.operand1+" "+this.userExer.operation+" "+this.userExer.operand2+" "+" = "+this.userExer.answer+" - правильно";
            this.userExer.okCount++;
        }
            else{
            this.userExer.checked = ""+this.userExer.operand1+" "+this.userExer.operation+" "+this.userExer.operand2+" "+" = "+this.userExer.answer+" - не правильно";
            this.userExer.erCount++;
        }

        model.addAttribute("userExer", this.userExer);
        return "check";
    }
}
