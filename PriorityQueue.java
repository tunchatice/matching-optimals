import java.util.ArrayList;

public class PriorityQueue {
    public ArrayList<Freelancer> freelancers;
    int size;
    PriorityQueue(){
        freelancers = new ArrayList<>();
        freelancers.add(null);
        size=0;
    }

    private boolean isBetter(Freelancer a, Freelancer b) {
        if (a.compositeScore() > b.compositeScore()) return true;
        if (a.compositeScore() < b.compositeScore()) return false;
        return a.getFreelancerID().compareTo(b.getFreelancerID()) < 0;
    }
    private void percolateDown( int hole )
    {
        int child;
        Freelancer tmp = freelancers.get(hole);
        for( ; hole * 2 <= size; hole = child )
        {
            child = hole * 2;
            if(child +1<=size &&isBetter(freelancers.get(child+1),(freelancers.get(child))))
                child++;
            if( isBetter(freelancers.get(child),tmp)){
                Freelancer change= freelancers.get(child);
                freelancers.set(hole,change);
                change.index=hole;
            }
            else
                break;
        }
        freelancers.set(hole,tmp);
        tmp.index=hole;
    }



    public void insert( Freelancer x )
    {// Percolate up
        int hole = ++size;
        if (hole >= freelancers.size()) {
            freelancers.add(null);
        }
        freelancers.set(0, x);

        for (; isBetter(x, freelancers.get(hole / 2)); hole /= 2) {
            Freelancer movedParent = freelancers.get(hole / 2);
            freelancers.set(hole, movedParent);
            movedParent.index = hole;
        }

        freelancers.set(hole, x);
        x.index=hole;
    }

    public Freelancer findMax() {
        if (size == 0) return null;
        return freelancers.get(1);
    }

    public Freelancer deleteMax() {
        if (size==0) return null;
        Freelancer max = findMax();
        freelancers.set(1,freelancers.get(size));
        size--;
        percolateDown(1);
        max.index=-1;
        return max;
    }

    public Freelancer deleteSpesific(int i) {
        if (i<1||i>size) return null;
        Freelancer delete=freelancers.get(i);
        delete.index=-1;

        //last element
        if (i == size) {
            freelancers.set(size, null);
            size--;
            return delete;}



        Freelancer lastE = freelancers.get(size);
        freelancers.set(size,null);
        size--;

        freelancers.set(i,lastE);
        lastE.index=i;


        int parent=i/2;
        if(i>1&&isBetter(lastE ,freelancers.get(parent))){
            freelancers.set(0, lastE);


            int hole = i;
            for (; isBetter(lastE, freelancers.get(hole / 2)); hole /= 2) {
                Freelancer movedParent = freelancers.get(hole / 2);
                freelancers.set(hole, movedParent);
                movedParent.index = hole;        // parent index'ini güncelle
            }

            freelancers.set(hole, lastE);
            lastE.index = hole;
        }
        else{
            percolateDown(i);
        }
        return delete;
    }

}

