/**
 * Copyright (c) 2016 Mark Rienstra
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.snowengine.objects.entities.AnimatedEntity;
import com.snowengine.objects.entities.EntityBase;

import java.util.Random;

public final class Slime extends AnimatedEntity
{
    private int m_Health;
    
    public Slime()
    {
        super ("textures/slime.png", 1, 3);
        m_Health = 5 + new Random().nextInt(3);
    }
    
    public void hit(EntityBase attacker)
    {
        m_Health -= 1;
    }
    
    @Override
    public void update()
    {
        super.update();
        
        if (m_Health <= 0)
        {
            this.destroy();
        }
    }
}
