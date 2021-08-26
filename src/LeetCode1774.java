public class LeetCode1774 {
    int diff=Integer.MAX_VALUE;//Difference
    int answer=Integer.MAX_VALUE;//Answer
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {

        for(int i=0;i<baseCosts.length;i++)
            lowest(toppingCosts,target,baseCosts[i],-1);//Calling the function for all the base toppings
        return answer;

    }

    public void lowest(int[] toppingCosts, int target,int val,int pos)
    {
        if(diff>Math.abs(target-val))//Update the difference and the answer
        {
            diff=Math.abs(target-val);
            answer=val;
        }

        if(diff==Math.abs(target-val))//If difference is the same as min return the minimum.
        {
            answer=Math.min(val,answer);
        }

        if(val>target)//No need to go further as the Difference between val and target would only increase.
        {
            return;
        }


        for(int i=pos+1;i<toppingCosts.length;i++)
        {
            lowest(toppingCosts,target,val+toppingCosts[i],i);//1 scoop of topping i.
            lowest(toppingCosts,target,val+toppingCosts[i]+toppingCosts[i],i);//2 scoops of topping i.
        }

        return;

    }
}
