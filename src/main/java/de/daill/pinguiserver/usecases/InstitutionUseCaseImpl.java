/*
 * Copyright 2019 Christian Kramer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 *  (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge,
 *  publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 *  so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package de.daill.pinguiserver.usecases;

import de.daill.pinguiserver.model.Institution;
import de.daill.pinguiserver.repos.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionUseCaseImpl implements InstitutionUseCase {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public List<Institution> findInstitutionsByRegex(String plzQry, String nameQry) {
        return institutionRepository.findByRegex(plzQry, nameQry);
    }

    @Override
    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public void saveInstitution(Institution institution) {
        this.institutionRepository.save(institution);
    }

    @Override
    public Optional<Institution> findById(String id) {
        return this.institutionRepository.findById(id);
    }


}
